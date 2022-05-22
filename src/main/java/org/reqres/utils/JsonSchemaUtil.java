package org.reqres.utils;

import org.reqres.exceptions.InvalidSchemaFilePathException;
import org.reqres.exceptions.SchemaFileNotFoundException;

import java.io.File;
import java.nio.file.NotDirectoryException;

public final class JsonSchemaUtil {

    private JsonSchemaUtil(){}

    public static String getSchemaFilepath(String filename) {
        String schemasPath = System.getProperty("user.dir") + "/src/test/resources/schemas";
        File schemaFolder = new File(schemasPath);
        try {
            if (!schemaFolder.exists()) {
                throw new InvalidSchemaFilePathException("schemas folder not found (" + schemasPath + ")");
            }
            if (!schemaFolder.isDirectory()) {
                throw new NotDirectoryException("SCHEMA_FILEPATH is not a valid directory");
            }
        } catch (InvalidSchemaFilePathException | NotDirectoryException e) {
            e.printStackTrace();
        }

        for (File file : schemaFolder.listFiles()) {
            if (file.isDirectory()) {
                String ret = searchInsideDirectory(file, filename);
                if (ret != null)
                    return ret;
            } else if (file.getName().equalsIgnoreCase(filename)) {
                return file.getAbsolutePath();
            }
        }
        throw new SchemaFileNotFoundException("the file '" + filename + "' was not found");
    }

    private static String searchInsideDirectory(File folder, String filename) {

        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                String ret = searchInsideDirectory(file, filename);
                if (ret != null)
                    return ret;
            } else if (file.getName().equalsIgnoreCase(filename)) {
                return file.getAbsolutePath();
            }
        }
        return null;
    }


}
