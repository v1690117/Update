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

package org.mxupdate.update.datamodel;

import java.io.IOException;

import org.mxupdate.mapping.TypeDef_mxJPO;
import org.mxupdate.update.util.DeltaUtil_mxJPO;
import org.mxupdate.update.util.MqlBuilder_mxJPO;
import org.mxupdate.update.util.ParameterCache_mxJPO;
import org.mxupdate.update.util.ParameterCache_mxJPO.ValueKeys;
import org.mxupdate.update.util.UpdateException_mxJPO;

/**
 * The class is used to evaluate information from date attributes within MX
 * used to export, delete and update a date attribute.
 *
 * @author The MxUpdate Team
 */
public class AttributeDate_mxJPO
    extends AbstractAttribute_mxJPO<AttributeDate_mxJPO>
{
    /** Range value flag. */
    private boolean rangeValue;

    /**
     * Constructor used to initialize the date attribute instance with
     * related type definition and attribute name.
     *
     * @param _typeDef  defines the related type definition
     * @param _mxName   MX name of the date attribute object
     */
    public AttributeDate_mxJPO(final TypeDef_mxJPO _typeDef,
                               final String _mxName)
    {
        super(_typeDef, _mxName, "date", "timestamp");
    }

    /**
     * The method parses the date attribute specific XML URLs. This includes
     * information about:
     * <ul>
     * <li>contains the attribute {@link #rangeValue rangevalues}?</li>
     * </ul>
     *
     * @param _paramCache   parameter cache with MX context
     * @param _url      URL to parse
     * @param _content  content of the URL to parse
     * @return <i>true</i> if <code>_url</code> could be parsed; otherwise
     *         <i>false</i>
     */
    @Override()
    protected boolean parse(final ParameterCache_mxJPO _paramCache,
                            final String _url,
                            final String _content)
    {
        final boolean parsed;
        if ("/attrValueType".equals(_url) && "2".equals(_content))  {
            this.rangeValue = true;
            parsed = true;
        } else  {
            parsed = super.parse(_paramCache, _url, _content);
        }
        return parsed;
    }

    /**
     * {@inheritDoc}
     * Appends the date attribute specific values. Following values are
     * written:
     * <li>{@link #rangeValue range value} flag (if
     *     {@link ValueKeys#DMAttrSupportsFlagRangeValue} is defined)</li>
     */
    @Override()
    protected void writeAttributeSpecificValues(final ParameterCache_mxJPO _paramCache,
                                                final Appendable _out)
        throws IOException
    {
        super.writeAttributeSpecificValues(_paramCache, _out);
        if (_paramCache.getValueBoolean(ValueKeys.DMAttrSupportsFlagRangeValue))  {
            _out.append("  ").append(this.rangeValue ? "" : "!").append("rangevalue\n");
        }
    }

    /**
     * {@inheritDoc}
     * Appends the date attribute specific delta values. Following values are
     * calculated:
     * <li>{@link #rangeValue range value} flag (if
     *     {@link ValueKeys#DMAttrSupportsFlagRangeValue} is defined)</li>
     */
    @Override()
    protected void calcDelta(final ParameterCache_mxJPO _paramCache,
                             final MqlBuilder_mxJPO _mql,
                             final AttributeDate_mxJPO _target)
        throws UpdateException_mxJPO
    {
        super.calcDelta(_paramCache, _mql, _target);

        if (_paramCache.getValueBoolean(ValueKeys.DMAttrSupportsFlagRangeValue))  {
            if (!this.rangeValue)  {
                DeltaUtil_mxJPO.calcFlagDelta(_mql, "rangevalue", _target.rangeValue, this.rangeValue);
            } else if (!_target.rangeValue)  {
                throw new UpdateException_mxJPO(
                        UpdateException_mxJPO.Error.ABSTRACTATTRIBUTE_UPDATE_RANGEVALUEFLAG_UPDATED,
                        this.getName());
            }
        }
    }
}
