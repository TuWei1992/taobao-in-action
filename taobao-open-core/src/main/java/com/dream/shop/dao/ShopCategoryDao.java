package com.dream.shop.dao;

import com.dream.rapid.base.BaseIbatisDao;
import com.dream.shop.model.ShopCategory;
import com.dream.shop.model.ShopCategoryCriteria;
import org.springframework.stereotype.Repository;

@Repository
public class ShopCategoryDao extends BaseIbatisDao<ShopCategory, Long, ShopCategoryCriteria> {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_SHOP_CATEGORY
     *
     *  Tue Oct 07 17:27:43 CST 2014
     */
    public ShopCategoryDao() {
        super();
    }

    protected void prepareObjectForSaveOrUpdate(ShopCategory record, ShopCategory first) {
        ;
    }

    protected String getIbatisSqlMapNamespace() {
        return "TOP_SHOP_CATEGORY";
    }

    protected ShopCategoryCriteria getCriteriaParam(ShopCategory record, ShopCategoryCriteria criteria) {
        return new UpdateByCriteriaParms(record,criteria);
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table TOP_SHOP_CATEGORY
     *
     *  Tue Oct 07 17:27:43 CST 2014
     */
    private static class UpdateByCriteriaParms extends ShopCategoryCriteria {
        private Object record;

        public UpdateByCriteriaParms(Object record, ShopCategoryCriteria criteria) {
            super(criteria);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}