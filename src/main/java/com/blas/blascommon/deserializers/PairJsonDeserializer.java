package com.blas.blascommon.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.springframework.data.util.Pair;

public class PairJsonDeserializer extends JsonDeserializer<Pair<?, ?>> {

  @Override
  public Pair<?, ?> deserialize(JsonParser jsonParser, DeserializationContext context)
      throws IOException {
    JsonNode node = jsonParser.getCodec().readTree(jsonParser);
    JsonNode firstNode = node.get("first");
    JsonNode secondNode = node.get("second");
    Object first = firstNode.traverse(jsonParser.getCodec()).readValueAs(Object.class);
    Object second = secondNode.traverse(jsonParser.getCodec()).readValueAs(Object.class);
    return Pair.of(first, second);
  }
}
