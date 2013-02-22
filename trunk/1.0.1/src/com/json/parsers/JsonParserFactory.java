/*   Copyright 2013 Rajesh Putta

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 * 
 * 
 */

package com.json.parsers;

import com.json.config.handlers.ConfigHandler;
import com.json.config.handlers.JsonConfigHandler;
import com.json.config.handlers.ValidationConfigType;
import com.json.config.handlers.XmlConfigHandler;

public class JsonParserFactory {
	private static JsonParserFactory instance=null;
	
	private JsonParserFactory()
	{
	}
	
	public static JsonParserFactory getInstance()
	{
		if(instance==null)
		{
			synchronized (JsonParserFactory.class) {
				if(instance==null)
				{
					instance=new JsonParserFactory();
				}
			}
		}
		
		return instance;
	}
	
	public JSONParser newJsonParser()
	{
		JSONParser parser=new JSONParser();
		
		ConfigHandler configHandler=new XmlConfigHandler();
		
		parser.setConfigHandler(configHandler);
		
		return parser;
	}
	
	public JSONParser newJsonParser(ValidationConfigType type)
	{
		JSONParser parser=new JSONParser();
		
		ConfigHandler configHandler=null;
		
		switch(type)
		{
			case JSON: configHandler=new JsonConfigHandler();
					   break;
			case XML:
			default: configHandler=new XmlConfigHandler();
		}

		configHandler.setParserSelfInstance(parser);
		
		parser.setConfigHandler(configHandler);
		
		return parser;
	}	
}
