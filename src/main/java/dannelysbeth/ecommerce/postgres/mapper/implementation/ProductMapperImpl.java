package dannelysbeth.ecommerce.postgres.mapper.implementation;

import dannelysbeth.ecommerce.postgres.exception.FileInputException;
import dannelysbeth.ecommerce.postgres.mapper.definition.ProductMapper;
import dannelysbeth.ecommerce.postgres.model.DTO.Feature;
import dannelysbeth.ecommerce.postgres.model.DTO.request.ProductRequest;
import dannelysbeth.ecommerce.postgres.model.Product;
import dannelysbeth.ecommerce.postgres.model.ProductItem;
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
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductMapperImpl implements ProductMapper {
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

    @Override
    public Set<ProductItem> transformFromRequest(List<ProductRequest> requests) {
        return requests.stream().map(req -> {
            return ProductItem.builder()
                    .product(Product.builder()
                            .id(req.getProductCode())
                            .category(req.getCategory())
                            .description(req.getDescription())
                            .price(req.getPrice())
                            .name(req.getName())
                            .build())
                    .sku(req.getSKU())
//                    .variationOptions(req.getFeatures())
                    .quantityInStock(req.getQuantityInStock())
                    .build();
        }).collect(Collectors.toSet());
    }

//    private List<Variation> getVariationsFromFeatures(List<Feature> features) {
//        return features.forEach(feature -> {
//            Variation.builder()
//                    .parameter(feature.getParameter())
//                    .variationOptions(VariationOption.builder()
//                            .
//                    )
//        });
//    }

//    private List<ProductItem> getProductItems(JSONArray obj) {
//        List<ProductItem> productItems = new ArrayList<>();
//
//        obj.forEach(product -> {
//            ProductItem productItem = ProductItem.builder()
//                    .product(this.getProduct((JSONObject) product))
//                    .SKU(this.getSKU((JSONObject) product))
//                    .quantityInStock(this.getQuantityInStock((JSONObject) product))
//                    .variation(this.getVariations((JSONObject) product))
//                    .build();
//            productItems.add(productItem);
//        });
//        return productItems;
//    }
//
//    private Product getProduct(JSONObject jsonObj) {
//
//        String productCode = jsonObj.get("productCode").toString();
//        String name = jsonObj.get("name").toString();
//        String description = jsonObj.get("description").toString();
//        double price = (double) jsonObj.get("price");
//        String category = jsonObj.get("category").toString();
//        return Product.builder()
//                .id(productCode)
//                .name(name)
//                .price(price)
//                .category(category)
//                .description(description)
//                .build();
//    }
//
//    private long getQuantityInStock(JSONObject jsonObject) {
//        return (long) jsonObject.get("quantityInStock");
//    }
//
//    private long getSKU(JSONObject jsonObject) {
//        return (long) jsonObject.get("SKU");
//    }
//
//    private List<Variation> getVariations(JSONObject jsonObj) {
//        List<Variation> variationList = new ArrayList<>();
//
//        JSONArray variationsJsonArr = (JSONArray) jsonObj.get("variation");
//        variationsJsonArr.forEach(varJson -> {
//            String parameter = ((JSONObject) varJson).get("parameter").toString();
//            JSONObject variationOptionJSONObj = (JSONObject) ((JSONObject) varJson).get("type");
//            String name = variationOptionJSONObj.get("name").toString();
//            variationList.add(Variation.builder()
//                    .parameter(parameter)
//                    .type(VariationOption.builder()
//                            .variation()
//                            .value(name)
//                            .build())
//                    .build()
//            );
//        });
//        return variationList;
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
            long quantityInStock = (long) jsonObj.get("quantityInStock");
            String SKU = jsonObj.get("SKU").toString();

            ProductRequest productItem = ProductRequest.builder()
                    .productCode(productCode)
                    .name(name)
                    .description(description)
                    .price(price)
                    .category(category)
                    .features(getFeatures(jsonObj))
                    .quantityInStock(quantityInStock)
                    .SKU(SKU)
                    .build();
            productItems.add(productItem);
        });
        return productItems;
    }

    private List<Feature> getFeatures(JSONObject jsonObj) {
        List<Feature> features = new ArrayList<>();

        JSONArray variationsJsonArr = (JSONArray) jsonObj.get("features");

        variationsJsonArr.forEach(varJson -> {
            String parameter = ((JSONObject) varJson).get("parameter").toString();
            String value = ((JSONObject) varJson).get("value").toString();
            features.add(Feature.builder()
                    .parameter(parameter)
                    .value(value)
                    .build()
            );
        });
        return features;
    }


}