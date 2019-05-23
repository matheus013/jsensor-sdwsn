package projects.SDWSN.service;

public interface Service<T> {

    public void turnOn() ;

    public void turnOff() ;

    public void decrease() ;

    public void increase() ;

    public T getValue() ;

    public boolean isStatus() ;

    public void setStatus(boolean status) ;

    public void setValue(T value);
}
