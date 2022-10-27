package org.example.models;

import lombok.Data;

@Data
public class ItemElement {

    private String vri_id;
    private String org_title;
    private String mit_number;
    private String mit_title;
    private String mit_notation;
    private String mi_modification;
    private String mi_number;
    private String verification_date;
    private String valid_date;
    private String result_docnum;
    private Boolean applicability;

    public String getInfo () {
        return "<b>Идентификатор элемента:</b> " + "\n" + this.getVri_id() + "\n\n" +
                "<b>Наименование организации-поверителя:</b> " + "\n" + this.getOrg_title() + "\n\n" +
                "<b>Регистрационный номер типа СИ:</b> " + "\n" + this.getMit_number() + "\n\n" +
                "<b>Наименование типа СИ:</b> " + "\n" + this.getMit_title() + "\n\n" +
                "<b>Обозначение типа СИ:</b> " + "\n" + this.getMit_notation() + "\n\n" +
                "<b>Модификация СИ:</b> " + "\n" + this.getMi_modification() + "\n\n" +
                "<b>Заводской/серийный номер:</b> " + "\n" + this.getMi_number() + "\n\n" +
                "<b>Дата поверки:</b> " + "\n" + this.getVerification_date() + "\n\n" +
                "<b>Действительна до:</b> " + "\n" + this.getValid_date() + "\n\n" +
                "<b>Номер свидетельства:</b> " + "\n" + this.getResult_docnum() + "\n\n" +
                "<b>Пригодность:</b> " + "\n" + ApplicabilityToEmoji.convert(this.getApplicability());
    }

}
