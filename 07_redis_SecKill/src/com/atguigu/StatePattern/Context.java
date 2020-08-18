package com.atguigu.StatePattern;

public class Context {
    private State state;

    public boolean doAction() {
        return state.choujiang();
    }
    public void setState(State state){
        this.state=state;
    }
}
