package smartphone_factory.entity.smartphone_entity;


import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Stream.of;

public final class SmartphoneRegistry {
    private static SmartphoneRegistry INSTANCE;

    private static final Class<? extends Smartphone>[] SMARTPHONE_CLASSES = new Class[]{
            NoNameChinaSmartphone.class,
            NoNameIndiaSmartphone.class,
            TaiwanSmartphone.class,
            TopKoreaSmartphone.class,
            TopUsaSmartphone.class
    };

    private SmartphoneRegistry() {
    }


    public static SmartphoneRegistry getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SmartphoneRegistry();
        }
        return INSTANCE;
    }


    public Map<String, List<Class<? extends Smartphone>>> smartphoneMapGroupedByCategory() {
        return of(SMARTPHONE_CLASSES)
                .collect(Collectors.groupingBy(s -> {
                    Annotation[] annotations = s.getAnnotations();
                    return switch (annotations[0].annotationType().getSimpleName()) {
                        case "BudgetSmartphone" -> "Budget Smartphone category";
                        case "MidTierSmartphone" -> "Mid-Tier Smartphone category";
                        case "FlagshipSmartphone" -> "Flagship Smartphone category";
                        default -> "Unknown";
                    };
                }));
    }

    public List<Class<? extends Smartphone>> getSmartphoneListByCategory(final String category) {
        return smartphoneMapGroupedByCategory().getOrDefault(category, List.of());
    }

}
