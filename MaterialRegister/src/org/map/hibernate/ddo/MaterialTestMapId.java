package org.map.hibernate.ddo;

import java.io.Serializable;
import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MaterialTestMapId implements Serializable, Comparable<MaterialTestMapId> {

    private static final long serialVersionUID = 1L;
    private SimpleStringProperty ctNumber;
    private SimpleIntegerProperty testCode;

    public MaterialTestMapId() {
        this.ctNumber = new SimpleStringProperty( "" );
        this.testCode = new SimpleIntegerProperty( 0 );
    }

    public MaterialTestMapId( String ctNumber, int testCode ) {
        this.ctNumber = new SimpleStringProperty( ctNumber );
        this.testCode = new SimpleIntegerProperty( testCode );
    }

    public String getCtNumber() {
        return this.ctNumber.get();
    }

    public void setCtNumber( String ctNumber ) {
        this.ctNumber.set( ctNumber );
    }

    public SimpleStringProperty ctNumberProperty() {
        return this.ctNumber;
    }

    public int getTestCode() {
        return this.testCode.get();
    }

    public void setTestCode( int testCode ) {
        this.testCode.set( testCode );
    }

    public SimpleIntegerProperty testCodeProperty() {
        return this.testCode;
    }

    @Override
    public String toString() {
        return "MaterialTestMapId{" + "ctNumber=" + ctNumber + ", testCode=" + testCode + '}';
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final MaterialTestMapId other = ( MaterialTestMapId ) obj;
        if ( !Objects.equals( this.ctNumber, other.ctNumber ) ) {
            return false;
        }
        if ( !Objects.equals( this.testCode, other.testCode ) ) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode( this.ctNumber );
        hash = 47 * hash + Objects.hashCode( this.testCode );
        return hash;
    }

    @Override
    public int compareTo( MaterialTestMapId o ) {
        if ( ctNumber.get().compareTo( o.getCtNumber() ) == 0 ) {
            return testCode.getValue().compareTo( Integer.valueOf( o.getTestCode() ) );
        }
        else {
            return ctNumber.get().compareTo( o.getCtNumber() );
        }
    }
}
