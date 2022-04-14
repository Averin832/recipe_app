package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Recipe;

public class RecipeValidator {

    public static List<String> validate(Recipe r){

        List<String> errors = new ArrayList<String>();

        String titleError = validateTitle(r.getTitle());
        if (!titleError.equals("")){
            errors.add(titleError);
        }

        String ingredientError = validateIngredient(r.getIngredient());
        if (!ingredientError.equals("")) {
            errors.add(ingredientError);
        }

        String howToError = validateHowTo(r.getHowTo());
        if (!howToError.equals("")) {
            errors.add(howToError);
        }

        return errors;
    }

    private static String validateTitle(String title) {

        if (title == null || title.equals("")) {
            return "料理名を入力してください";
        }

        return "";
    }

    private static String validateIngredient(String ingredient) {

        if (ingredient == null || ingredient.equals("")) {
            return "材料を入力してください";
        }

        return "";
    }

    private static String validateHowTo(String how_to) {
        if (how_to == null || how_to.equals("")) {
            return "作り方を入力してください";
        }

        return "";
    }

}
