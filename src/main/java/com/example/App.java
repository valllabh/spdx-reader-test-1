package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;

import org.spdx.jacksonstore.MultiFormatStore;
import org.spdx.jacksonstore.MultiFormatStore.Format;
import org.spdx.library.InvalidSPDXAnalysisException;
import org.spdx.library.Read;
import org.spdx.library.model.SpdxPackage;
import org.spdx.storage.simple.InMemSpdxStore;


public class App 
{
    public static void main( String[] args ) throws IOException, InvalidSPDXAnalysisException
    {
        File jsonFile = new File(args[0]);
		MultiFormatStore inputStore = new MultiFormatStore(new InMemSpdxStore(), Format.JSON_PRETTY);
		try (InputStream input = new FileInputStream(jsonFile)) {
			inputStore.deSerialize(input, false);
		}
		String documentUri = inputStore.getDocumentUris().get(0);
		

        Stream<SpdxPackage>  s = Read.getAllPackages(inputStore, documentUri);

        s.forEach((SpdxPackage item) -> {
            try {
                System.out.println(item.getName().get());
            } catch (InvalidSPDXAnalysisException ex) {
                ex.printStackTrace();
            }
        });

    }
}
