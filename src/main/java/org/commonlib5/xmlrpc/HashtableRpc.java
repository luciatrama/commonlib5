/*
 *  HashtableRpc.java
 *  Creato il 30-nov-2011, 19.20.22
 *
 *  Copyright (C) 2011 WinSOFT di Nicola De Nisco
 *
 *  Questo software è proprietà di Nicola De Nisco.
 *  I termini di ridistribuzione possono variare in base
 *  al tipo di contratto in essere fra Nicola De Nisco e
 *  il fruitore dello stesso.
 *
 *  Fare riferimento alla documentazione associata al contratto
 *  di committenza per ulteriori dettagli.
 */
package org.commonlib5.xmlrpc;

import java.util.*;
import java.util.Map.Entry;
import org.commonlib5.utils.DateTime;
import org.commonlib5.utils.StringOper;

/**
 * Hashtable con controllo sui valori inseriti.
 * I valori null non vengono inseriti e provocano
 * una rimozione della chiave.
 * Tutti i tipi semplici vengono convertiti in stringa,
 * compresi i valori Date (formattati ISO).
 * Map sono inserite sempre come hashtable.
 * List sono inserite sempre come vector.
 * Set sono inseriti sempre come vector.
 *
 * @author Nicola De Nisco
 */
public class HashtableRpc extends Hashtable<String, Object>
{
  public HashtableRpc()
  {
  }

  public HashtableRpc(Map<? extends String, ? extends Object> map)
  {
    this.putAll(map);
  }

  @Override
  public synchronized Object put(String key, Object value)
  {
    if(value == null)
      return remove(key);

    // caso speciale container wrappati in hashtable e vector
    if(value instanceof Map)
      return put(key, (Map) value);
    if(value instanceof List)
      return put(key, (List) value);
    if(value instanceof Set)
      return put(key, (Set) value);
    if(value instanceof Collection)
      return super.put(key, new Vector((Collection) value));

    // valori tipi base più pratici in forma di stringa
    if(value instanceof String)
      value = StringOper.okStrNull(value);
    else if(value instanceof java.sql.Date)
      value = DateTime.formatIso((java.sql.Date) value, "");
    else if(value instanceof java.sql.Timestamp)
      value = DateTime.formatIsoFull((java.sql.Timestamp) value, "");
    else if(value instanceof java.util.Date)
      value = DateTime.formatIsoFull((java.util.Date) value, "");

    if(value == null)
      return remove(key);

    return super.put(key, value);
  }

  @Override
  public synchronized void putAll(Map<? extends String, ? extends Object> t)
  {
    for(Entry<? extends String, ? extends Object> entry : t.entrySet())
    {
      String key = entry.getKey();
      Object val = entry.getValue();

      this.put(key, val);
    }
  }

  public synchronized Object put(String key, int value)
  {
    return super.put(key, Integer.toString(value));
  }

  public synchronized Object put(String key, long value)
  {
    return super.put(key, Long.toString(value));
  }

  public synchronized Object put(String key, double value)
  {
    return super.put(key, Double.toString(value));
  }

  public synchronized Object put(String key, boolean value)
  {
    return super.put(key, value ? "1" : "0");
  }

  public synchronized Object put(String key, Map value)
  {
    if(value == null)
      return remove(key);

    if(value instanceof Hashtable)
      return super.put(key, value);

    return super.put(key, new Hashtable(value));
  }

  public synchronized Object put(String key, List value)
  {
    if(value == null)
      return remove(key);

    if(value instanceof Vector)
      return super.put(key, value);

    return super.put(key, new Vector(value));
  }

  public synchronized Object put(String key, Set value)
  {
    if(value == null)
      return remove(key);

    return super.put(key, new Vector(value));
  }

  public String getAsString(String key)
  {
    return StringOper.okStrNull(get(key));
  }

  public int getAsInt(String key)
  {
    return StringOper.parse(get(key), 0);
  }

  public double getAsDouble(String key)
  {
    return StringOper.parse(get(key), 0.0);
  }

  public boolean getAsBoolean(String key)
  {
    return StringOper.checkTrueFalse(get(key), false);
  }

  public String getAsString(String key, String defVal)
  {
    return StringOper.okStr(get(key), defVal);
  }

  public int getAsInt(String key, int defVal)
  {
    return StringOper.parse(get(key), defVal);
  }

  public double getAsDouble(String key, double defVal)
  {
    return StringOper.parse(get(key), defVal);
  }

  public boolean getAsBoolean(String key, boolean defVal)
  {
    return StringOper.checkTrueFalse(get(key), defVal);
  }
}
