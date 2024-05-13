package dannelysbeth.ecommerce.postgres.factory.implemention;

import dannelysbeth.ecommerce.postgres.exception.FileInputException;
import dannelysbeth.ecommerce.postgres.factory.definition.JsonProductMapper;
import dannelysbeth.ecommerce.postgres.model.DTO.Feature;
import dannelysbeth.ecommerce.postgres.model.DTO.request.ProductItemRequest;
import dannelysbeth.ecommerce.postgres.model.DTO.request.ProductRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class JsonProductMapperImpl implements JsonProductMapper {
    @Override
    public List<ProductRequest> readFromFile(MultipartFile file) {
        JSONParser jsonParser = new JSONParser();

        try (Reader reader = new InputStreamReader(file.getInputStream())) {
            Object obj = jsonParser.parse(reader);

            return getProductRequests((JSONArray) obj);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new FileInputException(e.getMessage());
        }
    }

//    private List<ProductRequest> getProductRequests(JSONArray obj) {
//        List<ProductRequest> productItems = new ArrayList<>();
//
//        obj.forEach(product -> {
//            JSONObject jsonObj = (JSONObject) product;
//
//            String productCode = jsonObj.get("productCode").toString();
//            String name = jsonObj.get("name").toString();
//            String description = jsonObj.get("description").toString();
//            double price = (double) jsonObj.get("price");
//            String category = jsonObj.get("category").toString();
//            long quantityInStock = (long) jsonObj.get("quantityInStock");
//            String SKU = jsonObj.get("SKU").toString();
//
//            ProductRequest productItem = ProductRequest.builder()
//                    .productCode(productCode)
//                    .name(name)
//                    .description(description)
//                    .price(price)
//                    .category(category)
//                    .features(getFeatures(jsonObj))
//                    .quantityInStock(quantityInStock)
//                    .SKU(SKU)
//                    .build();
//            productItems.add(productItem);
//        });
//        return productItems;
//    }
//
//    private List<Feature> getFeatures(JSONObject jsonObj) {
//        List<Feature> features = new ArrayList<>();
//
//        JSONArray variationsJsonArr = (JSONArray) jsonObj.get("features");
//
//        variationsJsonArr.forEach(varJson -> {
//            String parameter = ((JSONObject) varJson).get("parameter").toString();
//            String value = ((JSONObject) varJson).get("value").toString();
//            features.add(Feature.builder()
//                    .parameter(parameter)
//                    .value(value)
//                    .build()
//            );
//        });
//        return features;
//    }

    private List<ProductRequest> getProductRequests(JSONArray obj) {
        List<ProductRequest> productItems = new ArrayList<>();

        obj.forEach(product -> {
            JSONObject jsonObj = (JSONObject) product;

            String productCode = jsonObj.get("productCode").toString();
            String name = jsonObj.get("name").toString();
            String description = jsonObj.get("description").toString();
            double price = (double) jsonObj.get("price");
            String category = jsonObj.get("category").toString();
            JSONArray productItemsJsonArr = (JSONArray) jsonObj.get("productItems");
            Set<ProductItemRequest> items = getProductItems(productItemsJsonArr);

            ProductRequest productItem = ProductRequest
                    .builder()
                    .productCode(productCode)
                    .name(name)
                    .description(description)
                    .price(price)
                    .category(category)
                    .productItems(items)
                    .build();
            productItems.add(productItem);
        });
        return productItems;
    }

    private Set<ProductItemRequest> getProductItems(JSONArray jsonArray) {
        Set<ProductItemRequest> productItems = new HashSet<>();
        jsonArray.forEach(item -> {
            JSONObject jsonObj = (JSONObject) item;
            long quantityInStock = (long) jsonObj.get("quantityInStock");
            String SKU = jsonObj.get("SKU").toString();
            double price = (double) jsonObj.get("price");
            JSONArray variationsJsonArr = (JSONArray) jsonObj.get("features");
            Set<Feature> features = getFeatures(variationsJsonArr);

            productItems.add(ProductItemRequest.builder()
                    .SKU(SKU)
                    .price(price)
                    .quantityInStock(quantityInStock)
                    .features(features)
                    .build());
        });
        return productItems;
    }

    private Set<Feature> getFeatures(JSONArray variationsJsonArr) {
        Set<Feature> features = new HashSet<>();
        variationsJsonArr.forEach(feature -> {
            JSONObject jsonObject = (JSONObject) feature;
            String parameter = jsonObject.get("parameter").toString();
            String value = jsonObject.get("value").toString();
            features.add(Feature.builder()
                    .parameter(parameter)
                    .value(value)
                    .build()
            );
        });
        return features;
    }
}
