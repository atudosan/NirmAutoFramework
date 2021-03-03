package io.nirmata.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import io.nirmata.constants.FrameworkConstants;
import io.nirmata.enums.ConfigProperties;
import io.nirmata.exceptions.InvalidFilePathException;
import io.nirmata.exceptions.PropertyFileUsageException;
import io.nirmata.exceptions.ReadingYourFileException;

public final class PropertyUtils {

	private PropertyUtils() {
	}

	private static Properties property = new Properties();
	private static final Map<String, String> CONFIGMAP = new HashMap<>();

	static {
		try(FileInputStream	file = new FileInputStream(FrameworkConstants.getConfigFilePath())){
			property.load(file);
			for (Map.Entry<Object, Object> entry : property.entrySet()) {
				CONFIGMAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()).trim());
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static String getValue(ConfigProperties key)  {
		if(Objects.isNull(key) || Objects.isNull(CONFIGMAP.get(key.name().toLowerCase()))) {
			throw new PropertyFileUsageException("Property name "+key+" is not found. Please check config.properites file");
		}
		return CONFIGMAP.get(key.name().toLowerCase());
	}

}
