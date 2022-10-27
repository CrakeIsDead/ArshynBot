package org.example.models;

public class ApplicabilityToEmoji {

    public static String convert(Boolean value) {
         if(value){
             return "✅";
         }else {
             return "❌";
         }
    }
}
