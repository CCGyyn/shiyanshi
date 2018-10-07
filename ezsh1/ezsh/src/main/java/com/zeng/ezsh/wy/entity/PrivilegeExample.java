package com.zeng.ezsh.wy.entity;

import java.util.ArrayList;
import java.util.List;

public class PrivilegeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PrivilegeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andPrivIdIsNull() {
            addCriterion("priv_id is null");
            return (Criteria) this;
        }

        public Criteria andPrivIdIsNotNull() {
            addCriterion("priv_id is not null");
            return (Criteria) this;
        }

        public Criteria andPrivIdEqualTo(String value) {
            addCriterion("priv_id =", value, "privId");
            return (Criteria) this;
        }

        public Criteria andPrivIdNotEqualTo(String value) {
            addCriterion("priv_id <>", value, "privId");
            return (Criteria) this;
        }

        public Criteria andPrivIdGreaterThan(String value) {
            addCriterion("priv_id >", value, "privId");
            return (Criteria) this;
        }

        public Criteria andPrivIdGreaterThanOrEqualTo(String value) {
            addCriterion("priv_id >=", value, "privId");
            return (Criteria) this;
        }

        public Criteria andPrivIdLessThan(String value) {
            addCriterion("priv_id <", value, "privId");
            return (Criteria) this;
        }

        public Criteria andPrivIdLessThanOrEqualTo(String value) {
            addCriterion("priv_id <=", value, "privId");
            return (Criteria) this;
        }

        public Criteria andPrivIdLike(String value) {
            addCriterion("priv_id like", value, "privId");
            return (Criteria) this;
        }

        public Criteria andPrivIdNotLike(String value) {
            addCriterion("priv_id not like", value, "privId");
            return (Criteria) this;
        }

        public Criteria andPrivIdIn(List<String> values) {
            addCriterion("priv_id in", values, "privId");
            return (Criteria) this;
        }

        public Criteria andPrivIdNotIn(List<String> values) {
            addCriterion("priv_id not in", values, "privId");
            return (Criteria) this;
        }

        public Criteria andPrivIdBetween(String value1, String value2) {
            addCriterion("priv_id between", value1, value2, "privId");
            return (Criteria) this;
        }

        public Criteria andPrivIdNotBetween(String value1, String value2) {
            addCriterion("priv_id not between", value1, value2, "privId");
            return (Criteria) this;
        }

        public Criteria andPrivModuleIsNull() {
            addCriterion("priv_module is null");
            return (Criteria) this;
        }

        public Criteria andPrivModuleIsNotNull() {
            addCriterion("priv_module is not null");
            return (Criteria) this;
        }

        public Criteria andPrivModuleEqualTo(String value) {
            addCriterion("priv_module =", value, "privModule");
            return (Criteria) this;
        }

        public Criteria andPrivModuleNotEqualTo(String value) {
            addCriterion("priv_module <>", value, "privModule");
            return (Criteria) this;
        }

        public Criteria andPrivModuleGreaterThan(String value) {
            addCriterion("priv_module >", value, "privModule");
            return (Criteria) this;
        }

        public Criteria andPrivModuleGreaterThanOrEqualTo(String value) {
            addCriterion("priv_module >=", value, "privModule");
            return (Criteria) this;
        }

        public Criteria andPrivModuleLessThan(String value) {
            addCriterion("priv_module <", value, "privModule");
            return (Criteria) this;
        }

        public Criteria andPrivModuleLessThanOrEqualTo(String value) {
            addCriterion("priv_module <=", value, "privModule");
            return (Criteria) this;
        }

        public Criteria andPrivModuleLike(String value) {
            addCriterion("priv_module like", value, "privModule");
            return (Criteria) this;
        }

        public Criteria andPrivModuleNotLike(String value) {
            addCriterion("priv_module not like", value, "privModule");
            return (Criteria) this;
        }

        public Criteria andPrivModuleIn(List<String> values) {
            addCriterion("priv_module in", values, "privModule");
            return (Criteria) this;
        }

        public Criteria andPrivModuleNotIn(List<String> values) {
            addCriterion("priv_module not in", values, "privModule");
            return (Criteria) this;
        }

        public Criteria andPrivModuleBetween(String value1, String value2) {
            addCriterion("priv_module between", value1, value2, "privModule");
            return (Criteria) this;
        }

        public Criteria andPrivModuleNotBetween(String value1, String value2) {
            addCriterion("priv_module not between", value1, value2, "privModule");
            return (Criteria) this;
        }

        public Criteria andOperationIsNull() {
            addCriterion("operation is null");
            return (Criteria) this;
        }

        public Criteria andOperationIsNotNull() {
            addCriterion("operation is not null");
            return (Criteria) this;
        }

        public Criteria andOperationEqualTo(String value) {
            addCriterion("operation =", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationNotEqualTo(String value) {
            addCriterion("operation <>", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationGreaterThan(String value) {
            addCriterion("operation >", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationGreaterThanOrEqualTo(String value) {
            addCriterion("operation >=", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationLessThan(String value) {
            addCriterion("operation <", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationLessThanOrEqualTo(String value) {
            addCriterion("operation <=", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationLike(String value) {
            addCriterion("operation like", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationNotLike(String value) {
            addCriterion("operation not like", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationIn(List<String> values) {
            addCriterion("operation in", values, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationNotIn(List<String> values) {
            addCriterion("operation not in", values, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationBetween(String value1, String value2) {
            addCriterion("operation between", value1, value2, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationNotBetween(String value1, String value2) {
            addCriterion("operation not between", value1, value2, "operation");
            return (Criteria) this;
        }

        public Criteria andPrivDescribeIsNull() {
            addCriterion("priv_describe is null");
            return (Criteria) this;
        }

        public Criteria andPrivDescribeIsNotNull() {
            addCriterion("priv_describe is not null");
            return (Criteria) this;
        }

        public Criteria andPrivDescribeEqualTo(String value) {
            addCriterion("priv_describe =", value, "privDescribe");
            return (Criteria) this;
        }

        public Criteria andPrivDescribeNotEqualTo(String value) {
            addCriterion("priv_describe <>", value, "privDescribe");
            return (Criteria) this;
        }

        public Criteria andPrivDescribeGreaterThan(String value) {
            addCriterion("priv_describe >", value, "privDescribe");
            return (Criteria) this;
        }

        public Criteria andPrivDescribeGreaterThanOrEqualTo(String value) {
            addCriterion("priv_describe >=", value, "privDescribe");
            return (Criteria) this;
        }

        public Criteria andPrivDescribeLessThan(String value) {
            addCriterion("priv_describe <", value, "privDescribe");
            return (Criteria) this;
        }

        public Criteria andPrivDescribeLessThanOrEqualTo(String value) {
            addCriterion("priv_describe <=", value, "privDescribe");
            return (Criteria) this;
        }

        public Criteria andPrivDescribeLike(String value) {
            addCriterion("priv_describe like", value, "privDescribe");
            return (Criteria) this;
        }

        public Criteria andPrivDescribeNotLike(String value) {
            addCriterion("priv_describe not like", value, "privDescribe");
            return (Criteria) this;
        }

        public Criteria andPrivDescribeIn(List<String> values) {
            addCriterion("priv_describe in", values, "privDescribe");
            return (Criteria) this;
        }

        public Criteria andPrivDescribeNotIn(List<String> values) {
            addCriterion("priv_describe not in", values, "privDescribe");
            return (Criteria) this;
        }

        public Criteria andPrivDescribeBetween(String value1, String value2) {
            addCriterion("priv_describe between", value1, value2, "privDescribe");
            return (Criteria) this;
        }

        public Criteria andPrivDescribeNotBetween(String value1, String value2) {
            addCriterion("priv_describe not between", value1, value2, "privDescribe");
            return (Criteria) this;
        }

        public Criteria andPPrivIdIsNull() {
            addCriterion("p_priv_id is null");
            return (Criteria) this;
        }

        public Criteria andPPrivIdIsNotNull() {
            addCriterion("p_priv_id is not null");
            return (Criteria) this;
        }

        public Criteria andPPrivIdEqualTo(String value) {
            addCriterion("p_priv_id =", value, "pPrivId");
            return (Criteria) this;
        }

        public Criteria andPPrivIdNotEqualTo(String value) {
            addCriterion("p_priv_id <>", value, "pPrivId");
            return (Criteria) this;
        }

        public Criteria andPPrivIdGreaterThan(String value) {
            addCriterion("p_priv_id >", value, "pPrivId");
            return (Criteria) this;
        }

        public Criteria andPPrivIdGreaterThanOrEqualTo(String value) {
            addCriterion("p_priv_id >=", value, "pPrivId");
            return (Criteria) this;
        }

        public Criteria andPPrivIdLessThan(String value) {
            addCriterion("p_priv_id <", value, "pPrivId");
            return (Criteria) this;
        }

        public Criteria andPPrivIdLessThanOrEqualTo(String value) {
            addCriterion("p_priv_id <=", value, "pPrivId");
            return (Criteria) this;
        }

        public Criteria andPPrivIdLike(String value) {
            addCriterion("p_priv_id like", value, "pPrivId");
            return (Criteria) this;
        }

        public Criteria andPPrivIdNotLike(String value) {
            addCriterion("p_priv_id not like", value, "pPrivId");
            return (Criteria) this;
        }

        public Criteria andPPrivIdIn(List<String> values) {
            addCriterion("p_priv_id in", values, "pPrivId");
            return (Criteria) this;
        }

        public Criteria andPPrivIdNotIn(List<String> values) {
            addCriterion("p_priv_id not in", values, "pPrivId");
            return (Criteria) this;
        }

        public Criteria andPPrivIdBetween(String value1, String value2) {
            addCriterion("p_priv_id between", value1, value2, "pPrivId");
            return (Criteria) this;
        }

        public Criteria andPPrivIdNotBetween(String value1, String value2) {
            addCriterion("p_priv_id not between", value1, value2, "pPrivId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}