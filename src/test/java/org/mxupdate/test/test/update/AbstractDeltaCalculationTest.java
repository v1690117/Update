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

package org.mxupdate.test.test.update;

import java.io.File;

import org.mxupdate.test.AbstractTest;
import org.mxupdate.test.ExportParser;
import org.mxupdate.test.data.AbstractData;
import org.mxupdate.update.AbstractObject_mxJPO;
import org.mxupdate.update.util.ParameterCache_mxJPO;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import matrix.util.MatrixException;

/**
 * Common definitions for delta calculation tests.
 *
 * @author The MxUpdate Team
 * @param <DATA> class of the data
 */
public abstract class AbstractDeltaCalculationTest<DATA extends AbstractObject_mxJPO<?>,TESTDATA extends AbstractData<?>>
    extends AbstractTest
{
    /**
     * Returns data providers used for testing parses.
     *
     * @return test source code to parse
     */
    @DataProvider(name = "data")
    public abstract Object[][] getData();

    /**
     * Creates for given {@code _name} related data instance.
     *
     * @param _paramCache   parameter cache
     * @param _name         name of the test object
     * @return new create data instance
     */
    protected abstract DATA createNewData(final ParameterCache_mxJPO _paramCache,
                                          final String _name);

    /**
     * Cleanups generated test data.
     *
     * @throws MatrixException if cleanup fails
     */
    @BeforeMethod()
    @AfterClass(groups = "close" )
    public abstract void cleanup()
            throws MatrixException;

    /**
     * Tests the delta calculation.
     *
     * @param _description  not used
     * @param _currentData  current starting data
     * @param _targetData   target and expected data
     * @throws Exception if test failed
     */
    @Test(dataProvider = "data")
    public void positivTest(final String _description,
                            final TESTDATA _currentData,
                            final TESTDATA _targetData)
        throws Exception
    {
        if (_currentData.isSupported(this.getVersion()) && _targetData.isSupported(this.getVersion()))  {
            final ParameterCache_mxJPO paramCache = new ParameterCache_mxJPO(this.getContext(), false);

            Assert.assertEquals(_currentData.getName(), _targetData.getName(), "check that ci names are equal");

            // create the depending objects to be able to connect to them
            _currentData.createDependings();
            _targetData.createDependings();

            // prepare the current
            final WrapperCIInstance<DATA> currentWrapper = new WrapperCIInstance<>(this.createNewData(paramCache, _currentData.getName()));
            currentWrapper.parseUpdate(_currentData);
            currentWrapper.create(paramCache);
            final WrapperCIInstance<DATA> tmp = new WrapperCIInstance<>(this.createNewData(paramCache, _currentData.getName()));
            tmp.parse(paramCache);
            currentWrapper.calcDelta(paramCache,  (File) null, tmp).exec(paramCache.getContext());
            _currentData.checkExport(new ExportParser(_currentData.getCI(), currentWrapper.write(paramCache), ""));

            // prepare the target form
            final WrapperCIInstance<DATA> targetWrapper = new WrapperCIInstance<>(this.createNewData(paramCache, _targetData.getName()));
            targetWrapper.parseUpdate(_targetData);

            // delta between current and target
            final WrapperCIInstance<DATA> tmp2 = new WrapperCIInstance<>(this.createNewData(paramCache, _targetData.getName()));
            tmp2.parse(paramCache);
            targetWrapper.calcDelta(paramCache, (File) null, tmp2).exec(paramCache.getContext());

            // check result from MX defined from calculated delta
            final WrapperCIInstance<DATA> resultWrapper = new WrapperCIInstance<>(this.createNewData(paramCache, _targetData.getName()));
            resultWrapper.parse(paramCache);
            _targetData.checkExport(new ExportParser(_targetData.getCI(), resultWrapper.write(paramCache), ""));
        }
    }
}
