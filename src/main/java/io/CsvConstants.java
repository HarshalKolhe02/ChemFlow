package io;

public final class CsvConstants {
    public static final String DELIMITER = ",";
    public static final String COMPONENT_HEADER = "Name,Formula,MolecularWeight";
    public static final String STREAM_HEADER = "Id,Name,MassFlowRate,Temperature,Pressure,Density,Viscosity";
    public static final String COMPOSITION_HEADER = "StreamId,Component,MassFraction";
    public static final String UNIT_HEADER = "Type,Id,Name,Inlet,Outlet,Parameter";
    private CsvConstants() {}
}