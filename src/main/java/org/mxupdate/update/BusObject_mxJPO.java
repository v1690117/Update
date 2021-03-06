/*
 *  This file is part of MxUpdate <http://www.mxupdate.org>.
 *
 *  MxUpdate is a deployment tool for a PLM platform to handle
 *  administration objects as single update files (configuration item).
 *
 *  Copyright (C) 2008-2016 The MxUpdate Team - All Rights Reserved
 *
 *  You may use, distribute and modify MxUpdate under the terms of the
 *  MxUpdate license. You should have received a copy of the MxUpdate
 *  license with this file. If not, please write to <info@mxupdate.org>,
 *  or visit <www.mxupdate.org>.
 *
 */

package org.mxupdate.update;

import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.mxupdate.mapping.PropertyDef_mxJPO;
import org.mxupdate.typedef.TypeDef_mxJPO;
import org.mxupdate.update.util.AbstractParser_mxJPO.ParseException;
import org.mxupdate.update.util.CompareToUtil_mxJPO;
import org.mxupdate.update.util.MqlUtil_mxJPO;
import org.mxupdate.update.util.ParameterCache_mxJPO;
import org.mxupdate.update.util.ParameterCache_mxJPO.ValueKeys;
import org.mxupdate.update.util.UpdateBuilder_mxJPO;
import org.mxupdate.update.util.UpdateBuilder_mxJPO.UpdateLine;
import org.mxupdate.update.util.UpdateException_mxJPO;
import org.mxupdate.update.zparser.MxParser_mxJPO;
import org.mxupdate.util.MqlBuilderUtil_mxJPO;
import org.mxupdate.util.MqlBuilderUtil_mxJPO.MultiLineMqlBuilder;

import matrix.db.Attribute;
import matrix.db.AttributeList;
import matrix.db.BusinessObject;
import matrix.db.BusinessObjectWithSelect;
import matrix.db.ExpansionWithSelect;
import matrix.db.RelationshipWithSelect;
import matrix.util.MatrixException;
import matrix.util.StringList;

/**
 * The class is used to export, create, delete and update business objects
 * within MX.
 *
 * @author The MxUpdate Team
 */
public class BusObject_mxJPO
    extends AbstractObject_mxJPO<BusObject_mxJPO>
{
    /** String used to split the name and revision of administration business object. */
    public static final String SPLIT_NAME = "________";
    /** String used to split the type and name with revision of administration business object. */
    public static final String SPLIT_TYPE = BusObject_mxJPO.SPLIT_NAME + BusObject_mxJPO.SPLIT_NAME;

    /** Parameter key to cache allowed attributes for relationships. */
    private static final String PARAM_RELATION_ATTRS = "BusRelationAttributes";

    /** Alphanumeric-comparator for string used within sorted sets. */
    private static final Comparator<String> COMP = new Comparator<String>() {
        @Override()
        public int compare(final String _name1, final String _name2)
        {
            final String name1 = _name1.replaceAll(" [0-9]*$", "");
            final String name2 = _name2.replaceAll(" [0-9]*$", "");
            int ret = 0;
            if (name1.equals(name2) && !name1.equals(_name1))  {
                final int index = name1.length();
                final Integer num1 = Integer.parseInt(_name1.substring(index).trim());
                final Integer num2 = Integer.parseInt(_name2.substring(index).trim());
                ret = num1.compareTo(num2);
            } else  {
                ret = _name1.compareTo(_name2);
            }
            return ret;
        }
    };

    /** Defines the related type definition enumeration. */
    private final TypeDef_mxJPO typeDef;

    /** Sorted set of attribute values.*/
    private final SortedMap<String,String> attrValues = new TreeMap<>(BusObject_mxJPO.COMP);

    /** Type of business object.*/
    private String busType;

    /** Name of business object */
    private final String busName;

    /** Revision of business object. */
    private final String busRevision;

    /** Current state of the related business object. */
    private String busCurrent;

    /** All possible states of the related business object. */
    private StringList busStates;

    /** Holds all connected objects. */
    private final SortedSet<Connection> connections = new TreeSet<>();

    /**
     * Constructor used to initialize the type definition enumeration.
     *
     * @param _typeDef  defines the related type definition enumeration
     * @param _mxName   MX name of the administration object
     */
    public BusObject_mxJPO(final TypeDef_mxJPO _typeDef,
                           final String _mxName)
    {
        super((_typeDef.hasMxBusTypeDerived() && (_mxName != null) && !_mxName.contains(BusObject_mxJPO.SPLIT_TYPE))
                      ? new StringBuilder().append(_typeDef.getMxBusType()).append(BusObject_mxJPO.SPLIT_TYPE).append(_mxName).toString()
                      : _mxName);

        this.typeDef = _typeDef;

        if (_mxName != null)  {
            if (_typeDef.hasMxBusTypeDerived())  {
                final String[] typeNameRev = this.getName().split(BusObject_mxJPO.SPLIT_TYPE);
                final String[] nameRev;
                if (typeNameRev.length == 2)  {
                    this.busType = typeNameRev[0];
                    nameRev = typeNameRev[1].split(BusObject_mxJPO.SPLIT_NAME);
                } else  {
                    this.busType = _typeDef.getMxBusType();
                    nameRev = this.getName().split(BusObject_mxJPO.SPLIT_NAME);
                }
                this.busName = nameRev[0];
                this.busRevision = (nameRev.length > 1) ? nameRev[1] : "";
            } else  {
                final String[] nameRev = this.getName().split(BusObject_mxJPO.SPLIT_NAME);
                this.busType = _typeDef.getMxBusType();
                this.busName = nameRev[0];
                this.busRevision = (nameRev.length > 1) ? nameRev[1] : "";
            }
        } else  {
            this.busType = _typeDef.getMxBusType();
            this.busName = null;
            this.busRevision = null;
        }
    }

    /**
     * Constructor used to parse.
     *
     * @param _typeDef      type definition
     * @param _busType      type of business object
     * @param _busName      name of business object
     * @param _busRevision  revision of business object
     */
    public BusObject_mxJPO(final TypeDef_mxJPO _typeDef,
                           final String _busType,
                           final String _busName,
                           final String _busRevision)
    {
        super(BusObject_mxJPO.prepMxName(_typeDef, _busType, _busName, _busRevision));
        this.typeDef = _typeDef;
        this.busType = _busType;
        this.busName = _busName;
        this.busRevision = _busRevision;
    }

    /**
     * Prepares the MX name for this business object.
     *
     * @param _typeDef      type definition
     * @param _busType      type of business object
     * @param _busName      name of business object
     * @param _busRevision  revision of business object
     * @return mx name
     */
    private static String prepMxName(final TypeDef_mxJPO _typeDef,
                                     final String _busType,
                                     final String _busName,
                                     final String _busRevision)
    {
        final StringBuilder ret = new StringBuilder();
        if (_typeDef.hasMxBusTypeDerived())  {
            ret.append(_busType).append(BusObject_mxJPO.SPLIT_TYPE);
        }
        return ret.append(_busName).append(BusObject_mxJPO.SPLIT_NAME).append(_busRevision).toString();
    }

    /**
     * Returns the {@link #typeDef type definition} instance.
     *
     * @return type definition enumeration
     */
    public final TypeDef_mxJPO getTypeDef()
    {
        return this.typeDef;
    }

    /**
     * {@inheritDoc}
     * <p>A <code>print bus</code> is internally made because the property is
     * defined as attribute on the business object.</p>
     */
    @Override()
    public String getPropValue(final ParameterCache_mxJPO _paramCache,
                               final PropertyDef_mxJPO _prop)
        throws MatrixException
    {
        return MqlBuilderUtil_mxJPO.mql()
                .cmd("escape print bus ").arg(this.busType).cmd(" ").arg(this.busName).cmd(" ").arg(this.busRevision)
                .cmd(" select ").arg("attribute[" + _prop.getAttrName(_paramCache) + "]").cmd(" dump")
                .exec(_paramCache.getContext());
    }

    /**
     * Parses all information for given administration object.
     * Sorts the attribute values, defines the description for the TCL
     * update script (concatenation of the revision and description).
     *
     * @param _paramCache   parameter cache
     * @throws MatrixException if the parse failed
     */
    @Override()
    public void parse(final ParameterCache_mxJPO _paramCache)
        throws MatrixException
    {
        final BusinessObject bus = new BusinessObject(this.busType, this.busName, this.busRevision, null);
        // id, vault, description and current MX state
        final StringList select = new StringList(4);
        select.addElement("description");
        select.addElement("current");
        select.addElement("policy.state");
        final BusinessObjectWithSelect sel = bus.select(_paramCache.getContext(), select);
        this.setDescription(sel.getSelectData("description"));
        this.busCurrent = sel.getSelectData("current");
        this.busStates = sel.getSelectDataList("policy.state");

        // define properties (attribute values)
        final AttributeList attrs = bus.getAttributeValues(_paramCache.getContext(), true);
        for (final Object attrObj : attrs)  {
            final Attribute attr = (Attribute) attrObj;
            this.attrValues.put(attr.getName(), attr.getValue());
        }

        // evaluate from connections
        if (this.getTypeDef().getMxBusRelsFrom() != null)  {
            for (final String relation : this.getTypeDef().getMxBusRelsFrom())  {
                this.expand4Parse(_paramCache, bus, relation, true, false);
            }
        }

        // evaluate to connections
        if (this.getTypeDef().getMxBusRelsTo() != null)  {
            for (final String relation : this.getTypeDef().getMxBusRelsTo())  {
                this.expand4Parse(_paramCache, bus, relation, false, true);
            }
        }

        // evaluate both connections
        if (this.getTypeDef().getMxBusRelsBoth() != null)  {
            for (final String relation : this.getTypeDef().getMxBusRelsBoth())  {
                // from
                this.expand4Parse(_paramCache, bus, relation, true, false);
                // to
                this.expand4Parse(_paramCache, bus, relation, false, true);
            }
        }
    }

    /**
     * Expands from the business object depending on the relationship and
     * stores the connection information. If the relationship has attributes,
     * the related values are also evaluated.
     *
     * @param _paramCache   parameter cache
     * @param _bus          the business object to expand
     * @param _relation     name of relationship to expand
     * @param _getFrom      must be set to <i>true</i> to expand from
     *                      connections; otherwise <i>false</i>
     * @param _getTo        must be set to <i>true</i> to expand to
     *                      connections; otherwise <i>false</i>
     * @throws MatrixException if the expand failed
     * @see #connections
     */
    @SuppressWarnings("unchecked")
    private void expand4Parse(final ParameterCache_mxJPO _paramCache,
                              final BusinessObject _bus,
                              final String _relation,
                              final boolean _getFrom,
                              final boolean _getTo)
        throws MatrixException
    {
        // get attributes from relationship
        final Map<String,Map> cache = _paramCache.defineValueMap(BusObject_mxJPO.PARAM_RELATION_ATTRS, Map.class);
        Map<String,String> attrs = cache.get(_relation);
        if (attrs == null)
        {
            attrs = new HashMap<>();
            cache.put(_relation, attrs);

            final String attrStr = MqlUtil_mxJPO.execMql(_paramCache,
                    new StringBuilder("escape print rel \"").append(_relation)
                            .append("\" select attribute dump '\n'"));
            if (!"".equals(attrStr))  {
                for (final String attr : attrStr.split("\n"))  {
                    attrs.put("attribute[" + attr + "]", attr);
                }
            };
        }

        final StringList busSelect = new StringList(3);
        busSelect.addElement("type");
        busSelect.addElement("name");
        busSelect.addElement("revision");
        final StringList relSelect = new StringList(1 + attrs.size());
        relSelect.addElement("id");
        relSelect.addAll(attrs.keySet());
        // get from objects
        final ExpansionWithSelect expandTo = _bus.expandSelect(_paramCache.getContext(),
                                                              _relation,
                                                              "*",
                                                              busSelect,
                                                              relSelect,
                                                              _getFrom,
                                                              _getTo,
                                                              (short) 1,
                                                              null,
                                                              null,
                                                              (short) 0,
                                                              true);

        for (final Object obj : expandTo.getRelationships())  {
            this.connections.add(new Connection(_relation, _getFrom ? "from" : "to", (RelationshipWithSelect) obj, attrs));
        }

    }

    /**
     * Deletes administration business object from given type with given name.
     *
     * @param _paramCache   parameter cache
     * @throws Exception if delete of the business object failed
     */
    @Override()
    public void delete(final ParameterCache_mxJPO _paramCache)
        throws Exception
    {
        final BusinessObject bus = new BusinessObject(this.busType, this.busName, this.busRevision, null);
        bus.remove(_paramCache.getContext());
    }

    /**
     * Creates for given name the business object.
     *
     * @param _paramCache   parameter cache
     * @throws Exception if the business object could not be created
     */
    @Override()
    public void createOld(final ParameterCache_mxJPO _paramCache)
        throws Exception
    {
        final BusinessObject bus = new BusinessObject(this.busType, this.busName, this.busRevision, this.getTypeDef().getMxBusVault());
        bus.create(_paramCache.getContext(), this.getTypeDef().getMxBusPolicy());
    }

    /**
     * Returns the business type of this business object instance.
     *
     * @return business type
     */
    public String getBusType()
    {
        return this.busType;
    }

    /**
     * Getter method for instance variable {@link #busName}.
     *
     * @return value of instance variable {@link #busName}
     */
    public String getBusName()
    {
        return this.busName;
    }

    /**
     * Getter method for instance variable {@link #busRevision}. If
     * {@link #busRevision} is <code>null</code> an empty string "" is returned.
     *
     * @return value of instance variable {@link #busRevision}
     */
    public String getBusRevision()
    {
        return (this.busRevision == null)
               ? ""
               : this.busRevision;
    }

    /**
     * Getter method for instance variable {@link #attrValuesSorted}.
     *
     * @return value of instance variable {@link #attrValuesSorted}
     */
    public SortedMap<String,String> getAttrValues()
    {
        return this.attrValues;
    }

    @Override
    public void parseUpdate(final String _code)
        throws SecurityException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ParseException
    {
        new MxParser_mxJPO(new StringReader(_code)).parseBusObject(this);
    }

    @Override
    public void writeUpdate(final UpdateBuilder_mxJPO _updateBuilder)
    {
        _updateBuilder
                //              tag             | default | value                              | write?
                .stringIfTrue(  "type",                     this.getBusType(),                  this.getTypeDef().hasMxBusTypeDerived())
                .string(        "description",              this.getDescription())
                .string(        "current",                  this.busCurrent);

        // system properties are not written
        final Set<String> notWriteProps = new HashSet<>();
        for (final PropertyDef_mxJPO propDef : PropertyDef_mxJPO.values())  {
            final String attrName = propDef.getAttrName(_updateBuilder.getParamCache());
            if (attrName != null)  {
                notWriteProps.add(attrName);
            }
        }
        // write information properties
        if (_updateBuilder.getParamCache().contains(ValueKeys.ExportInfoPropsListBus))  {
            notWriteProps.addAll(_updateBuilder.getParamCache().getValueList(ValueKeys.ExportInfoPropsListBus));
            boolean found = false;
            for (final String propKey : _updateBuilder.getParamCache().getValueList(ValueKeys.ExportInfoPropsListBus))  {
                if (this.attrValues.containsKey(propKey))  {
                    if (!found)  {
                        _updateBuilder.stepStartNewLine().stepSingle(_updateBuilder.getParamCache().getValueString(ValueKeys.ExportInfoPropsTextStart)).stepEndLine();
                        found = true;
                    }
                    _updateBuilder.stepStartNewLine().stepSingle("attribute").stepString(propKey).stepString(this.attrValues.get(propKey)).stepEndLine();
                }
            }
            if (found)  {
                _updateBuilder.stepStartNewLine().stepSingle(_updateBuilder.getParamCache().getValueString(ValueKeys.ExportInfoPropsTextEnd)).stepEndLine();
            }
        }
        // now the rest of the properties w/o info properties
        for (final Entry<String,String> entry : this.attrValues.entrySet())  {
            if (!notWriteProps.contains(entry.getKey()))  {
                if (this.getTypeDef().getMxBusIgnoredAttributes().contains(entry.getKey()))  {
                    _updateBuilder.stepStartNewLine().stepSingle(_updateBuilder.getParamCache().getValueString(ValueKeys.ExportBusIgnoredAttrText)).stepEndLine();
                }
                _updateBuilder.stepStartNewLine().stepSingle("attribute").stepString(entry.getKey()).stepString(entry.getValue()).stepEndLine();
            }
        }

        _updateBuilder
                .list(this.connections);
    }

    @Override
    public void calcDelta(final ParameterCache_mxJPO _paramCache,
                          final MultiLineMqlBuilder _mql,
                          final BusObject_mxJPO _current)
        throws UpdateException_mxJPO
    {
        _mql.newLine();

        if (CompareToUtil_mxJPO.compareWithNullAsEmpty(0, this.getDescription() , _current.getDescription()) != 0)  {
            _mql.cmd("description ").arg(this.getDescription()).cmd(" ");
        }

        for (final Entry<String,String> attrVal : this.attrValues.entrySet())  {
            if (CompareToUtil_mxJPO.compareWithNullAsEmpty(0, attrVal.getValue(), _current.attrValues.get(attrVal.getKey())) != 0)  {
                _mql.arg(attrVal.getKey()).cmd(" ").arg(attrVal.getValue()).cmd(" ");
            }
        }

        // promote / demote
        if (CompareToUtil_mxJPO.compareWithNullAsEmpty(0, this.busCurrent,_current.busCurrent) != 0)  {
            _mql.pushPrefix("");
            final int idxTar = _current.busStates.indexOf(this.busCurrent);
            int idxCur = _current.busStates.indexOf(_current.busCurrent);
            while (idxTar > idxCur)  {
                _mql.newLine().cmd("escape promote bus ").arg(this.busType).cmd(" ").arg(this.busName).cmd(" ").arg(this.busRevision);
                idxCur++;
            }
            while (idxTar < idxCur)  {
                _mql.newLine().cmd("escape demote bus ").arg(this.busType).cmd(" ").arg(this.busName).cmd(" ").arg(this.busRevision);
                idxCur--;
            }
            _mql.popPrefix();
        }

        // remove obsolete connections
        for (final Connection curCon : _current.connections)  {
            boolean found = false;
            for (final Connection newCon : this.connections)  {
                if (curCon.compareTo(newCon) == 0)  {
                    found = true;
                    break;
                }
            }
            if (!found)  {
                _mql.pushPrefix("")
                    .newLine()
                    .cmd("escape delete connection ").arg(curCon.conId)
                    .popPrefix();
            }
        }
        // assign new connections
        for (final Connection newCon : this.connections)  {
            boolean found = false;
                for (final Connection curCon : _current.connections)  {
                if (curCon.compareTo(newCon) == 0)  {
                    found = true;
                    break;
                }
            }
            if (!found)  {
                _mql.pushPrefix("")
                    .newLine()
                    .cmd("escape connect bus ").arg(this.busType).cmd(" ").arg(this.busName).cmd(" ").arg(this.busRevision)
                            .cmd(" relationship ").arg(newCon.relName).cmd(" ").cmd(newCon.direction)
                            .cmd(" ").arg(newCon.type).cmd(" ").arg(newCon.name).cmd(" ").arg(newCon.revision);
                for (final Entry<String,String> attrVal : newCon.attrValues.entrySet())  {
                        _mql.cmd(" ").arg(attrVal.getKey()).cmd(" ").arg(attrVal.getValue());
                }
                _mql.popPrefix();
            }
        }
    }

    /**
     * The class is used to store information about one connection including
     * all attributes values and the type, name with revision of the connected
     * business object.
     */
    public static class Connection
        implements Comparable<Connection>, UpdateLine
    {
        /** Name of the relationship. */
        private final String relName;

        /** Direction of the connection. */
        private final String direction;

        /** Type of the business object. */
        private final String type;
        /** Name of the business object. */
        private final String name;
        /** Revision of the business object. */
        private final String revision;

        /** Id of the connection. */
        private final String conId;

        /** Set of all attribute values of this connection. */
        private final SortedMap<String,String> attrValues = new TreeMap<>(BusObject_mxJPO.COMP);

        /**
         * Constructor to create a new connection instance.
         *
         * @param _relName      name of the relationship
         * @param _direction    direction of the connection
         * @param _relSelect    relationship selection
         * @param _attrs        selected attributes within the relationship
         *                      selection
         */
        public Connection(final String _relName,
                          final String _direction,
                          final RelationshipWithSelect _relSelect,
                          final Map<String,String> _attrs)
        {
            this.relName = _relName;
            this.direction = _direction;
            final BusinessObjectWithSelect busSelect = _relSelect.getTarget();
            this.type = busSelect.getSelectData("type");
            this.name = busSelect.getSelectData("name");
            this.revision = busSelect.getSelectData("revision");
            this.conId = _relSelect.getSelectData("id");
            this.attrValues.putAll(this.attrValues);
        }

        /**
         * Constructor used by the Parser
         */
        public Connection()
        {
            this.relName = "";
            this.direction = "";
            this.type = "";
            this.name = "";
            this.revision = "";
            this.conId ="";
        }

        /**
         * Returns the string representation of the business object. The string
         * representation is a concatenation of {@link #type}, {@link #name} and
         * {@link #revision}.
         *
         * @return string representation of the business object
         * @see #type
         * @see #name
         * @see #revision
         */
        @Override()
        public String toString()
        {
            return "[type = " + this.type + ","
                   + " name = " + this.name + ","
                   + " revision = " + this.revision + "]";
        }

        /**
         * Compares given connection with this connection. The compare is done
         * in this order:
         * <ul>
         * <li>compare connection direction</li>
         * <li>if equal compare relationship name</li>
         * <li>if equal compare business object type</li>
         * <li>if equal compare business object name</li>
         * <li>if equal compare business object revision</li>
         * <li>if equal compare attributes with {@link #compareToAttr}</li>
         * </ul>
         *
         * @param _compare      connection instance to compare
         * @return <code>0</code> if the connections are equal (should not
         *         possible...); a value less than <code>0</code> if this
         *         connection is lexicographically less than the compared
         *         connection; a value greater than <code>0</code> if this
         *         connection is lexicographically greater than the compared
         *         connection
         * @see #compareToAttr
         */
        @Override()
        public int compareTo(final Connection _compare)
        {
            return this.direction.equals(_compare.direction)
                   ? this.relName.equals(_compare.relName)
                       ? this.type.equals(_compare.type)
                           ? this.name.equals(_compare.name)
                               ? this.revision.equals(_compare.revision)
                                   ? this.compareToAttr(_compare)
                                   : this.revision.compareTo(_compare.revision)
                               : this.name.compareTo(_compare.name)
                           : this.type.compareTo(_compare.type)
                       : this.relName.compareTo(_compare.relName)
                   : this.direction.compareTo(_compare.direction);
        }

        /**
         * Compares all attributes values from this connection with the values
         * from the compared connection. If all values of both connections are
         * equal, the connection id's are compared. The attribute values are
         * compare in the order of the attributes (by attribute names).
         *
         * @param _compare  connection to compare
         * @return <code>0</code> if all attributes values and both connection
         *         id's are equal (should not possible...); a value less than
         *         <code>0</code> if one attribute value is lexicographically
         *         less than the compared attribute value; a value greater than
         *         <code>0</code> if one attribute value is lexicographically
         *         greater than the other attribute value
         */
        protected int compareToAttr(final Connection _compare)
        {
            final Iterator<Entry<String,String>> thisIter = this.attrValues.entrySet().iterator();
            final Iterator<Entry<String,String>> compIter = _compare.attrValues.entrySet().iterator();
            int ret = 0;
            while (thisIter.hasNext() && (ret == 0))  {
                ret = BusObject_mxJPO.COMP.compare(thisIter.next().getValue(), compIter.next().getValue());
            }
            return (ret == 0)
                   ? this.conId.compareTo(_compare.conId)
                   : ret;
        }

        @Override()
        public void write(final UpdateBuilder_mxJPO _updateBuilder)
        {
            _updateBuilder
                    .stepStartNewLine().stepSingle("connection").stepString(this.relName).stepSingle(this.direction)
                    .stepString(this.type).stepString(this.name).stepString(this.revision);
            if (this.attrValues.isEmpty())  {
                _updateBuilder.stepEndLine();
            } else  {
                _updateBuilder.stepEndLineWithStartChild();
                for (final Entry<String,String> entry : this.attrValues.entrySet())  {
                    _updateBuilder.stepStartNewLine().stepSingle("attribute").stepString(entry.getKey()).stepString(entry.getValue()).stepEndLine();
                }
                _updateBuilder.childEnd();
            }
        }
    }
}
