package org.example.factory;

import org.example.writer.CSVWriter;
import org.example.writer.FileWriter;

public class CSVWriterFactory implements FileWriterFactory{
    @Override
    public FileWriter createFileWriter() {
        return new CSVWriter();
    }
}
