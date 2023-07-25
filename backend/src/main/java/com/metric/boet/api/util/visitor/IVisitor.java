package com.metric.boet.api.util.visitor;

public interface IVisitor <REQ, RESP>{
    RESP visit(REQ request);
}
