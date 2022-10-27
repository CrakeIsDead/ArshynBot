package org.example.resources;

public class Constants {

    public static final String GREETING_TEXT = "Привет!\n Я - неофициальный бот ФГИС Аршин.\n" +
            "Я работаю в тестовом режиме, но уже могу помочь тебе найти результаты поверки СИ по заводскому (серийному) номеру.\n" +
            "Нажми на кнопку \"Поиск по заводскому номеру\" и следуй инструкциям.";

    public static final String FGIS_API_URL = "https://fgis.gost.ru/fundmetrology/eapi/vri";

    public static final String USER_PARAMS_NAME = "User-Agent";

    public static final String USER_PARAMS_VALUE = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11";

    public static final String SEARCH_PREFIX = "?search=";

    public static final String SEARCH_BUTTON_TEXT = "Поиск по заводскому номеру";

    public static final String SEARCH_BUTTON_DATA = "search_mi_number";

    public static final String SEARCH_TEXT_HINT = "Введите заводской номер средства измерения" +
            " и нажмите \"Отправить\".\n" + "(Поиск производится по результатам поверки в текущем году)";

    public static final String NO_SEARCH_RESULT = "Результатов не найдено.\nПопробуйте заменить латиницу на кириллицу или наоборт.";

    public static final String NEXT_BUTTON_DATA = "next_page";

    public static final String PREVIOUS_BUTTON_DATA = "prev_page";

    public static final String NEXT_BUTTON_TEXT = ">";

    public static final String PREVIOUS_BUTTON_TEXT = "<";


}
