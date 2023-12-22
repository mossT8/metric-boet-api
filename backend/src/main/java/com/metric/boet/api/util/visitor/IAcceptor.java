package com.metric.boet.api.util.visitor;

public interface IAcceptor<RESP> {
    RESP accept(IVisitor<?, RESP> visitor);
}
