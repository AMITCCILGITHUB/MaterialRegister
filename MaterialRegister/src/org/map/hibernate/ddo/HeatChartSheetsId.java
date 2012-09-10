package org.map.hibernate.ddo;

import java.io.Serializable;
import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class HeatChartSheetsId implements Serializable, Comparable<HeatChartSheetsId> {

    private static final long serialVersionUID = 1L;
    private SimpleStringProperty chartNumber;
    private SimpleIntegerProperty sheetNumber;
    private SimpleStringProperty ctNumber;

    public HeatChartSheetsId() {
        this.chartNumber = new SimpleStringProperty( "" );
        this.sheetNumber = new SimpleIntegerProperty( 1 );
        this.ctNumber = new SimpleStringProperty( "" );
    }

    public HeatChartSheetsId( String chartNumber, int sheetNumber, String ctNumber ) {
        this.chartNumber = new SimpleStringProperty( chartNumber );
        this.sheetNumber = new SimpleIntegerProperty( sheetNumber );
        this.ctNumber = new SimpleStringProperty( ctNumber );
    }

    public String getChartNumber() {
        return this.chartNumber.get();
    }

    public void setChartNumber( String chartNumber ) {
        this.chartNumber.set( chartNumber );
    }

    public SimpleStringProperty chartNumberProperty() {
        return this.chartNumber;
    }

    public int getSheetNumber() {
        return this.sheetNumber.get();
    }

    public void setSheetNumber( int sheetNumber ) {
        this.sheetNumber.set( sheetNumber );
    }

    public SimpleIntegerProperty sheetNumberProperty() {
        return this.sheetNumber;
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

    public void resetHeatChartSheetsId( HeatChartSheetsId hSheetsId ) {
        this.chartNumber = new SimpleStringProperty( hSheetsId.getChartNumber() );
        this.sheetNumber = new SimpleIntegerProperty( hSheetsId.getSheetNumber() );
        this.ctNumber = new SimpleStringProperty( hSheetsId.getCtNumber() );
    }

    @Override
    public String toString() {
        return "[1]" + chartNumber.get() + "[2]" + sheetNumber.get() + "[3]" + ctNumber.get();
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final HeatChartSheetsId other = ( HeatChartSheetsId ) obj;
        if ( !Objects.equals( this.chartNumber, other.chartNumber ) ) {
            return false;
        }
        if ( !Objects.equals( this.sheetNumber, other.sheetNumber ) ) {
            return false;
        }
        if ( !Objects.equals( this.ctNumber, other.ctNumber ) ) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode( this.chartNumber );
        hash = 23 * hash + Objects.hashCode( this.sheetNumber );
        hash = 23 * hash + Objects.hashCode( this.ctNumber );
        return hash;
    }

    @Override
    public int compareTo( HeatChartSheetsId o ) {
        if ( o instanceof HeatChartSheetsId && o != null ) {
            return this.sheetNumber.getValue().compareTo( o.sheetNumberProperty().getValue() );
        }
        else {
            throw new UnsupportedOperationException( "Not supported yet." );
        }
    }
}
