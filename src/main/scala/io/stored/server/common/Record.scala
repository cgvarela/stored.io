package io.stored.server.common

import collection.mutable.HashMap
import org.json.JSONObject
import io.stored.common.CryptoUtil
import util.parsing.json.JSONArray


object Record {
  def flatten(jsonData: JSONObject) : Map[String, AnyRef] = {
    flatten(jsonData, "", new HashMap[String, AnyRef])
  }

  def flatten(jsonData: JSONObject, path: String, map: HashMap[String, AnyRef]) : Map[String, AnyRef] = {
    val iter = jsonData.keys()

    while(iter.hasNext) {
      val key = iter.next().asInstanceOf[String]
      val refPath = "%s%s".format(path, key.toUpperCase)
      val ref = jsonData.get(key)
      if (ref.isInstanceOf[JSONObject]) {
        flatten(ref.asInstanceOf[JSONObject], refPath + "__", map)
      } else if (ref.isInstanceOf[JSONArray]) {
        // TODO: make this work
        println("skipping jsonarray for key: " + refPath)
      } else {
        if (ref.isInstanceOf[String]
            || ref.isInstanceOf[Long]
            || ref.isInstanceOf[Int]
            || ref.isInstanceOf[Double]
            || ref.isInstanceOf[Boolean]) {
          map.put(refPath, ref)
        } else {
//          println("skipping key: " + refPath)
        }
      }
    }

    map.toMap
  }

  def create(id: String, rawData: String) : Record = {
    val jsonData = new JSONObject(rawData)
    val colMap = flatten(jsonData)
    new Record(id, colMap, jsonData)
  }

  def create(rawData: String) : Record = {
    val id = CryptoUtil.computeHash(rawData.getBytes("UTF-8"))
    val jsonData = new JSONObject(rawData)
    val colMap = flatten(jsonData)
    new Record(id, colMap, jsonData)
  }

  def create(jsonData: JSONObject) : Record = {
    val hash = CryptoUtil.computeHash(jsonData.toString.getBytes("UTF-8"))
    val keyMap = flatten(jsonData)
    new Record(hash, keyMap, jsonData)
  }

  def create(id: String, jsonData: JSONObject) : Record = {
    val keyMap = flatten(jsonData)
    new Record(id, keyMap, jsonData)
  }
}

class Record(val id: String, val colMap: Map[String, AnyRef], val rawData: JSONObject) {}
