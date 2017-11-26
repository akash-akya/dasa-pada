/*
 * vachana. An application for Android users, it contains kannada vachanas
 * Copyright (c) 2016. akash
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.akash.dasapada.dbUtil;

import java.io.Serializable;
import java.util.EnumMap;

public class KathruDetails implements Serializable{
    private static final String TAG = "KathruDetails";

    public static String textOf(KEYS key) {
        switch (key){
            case SIBLINGS: return "ಒಡಹುಟ್ಟಿದವರು";
            case BIRTH_PLACE: return "ಜನ್ಮ ಸ್ಥಳ";
            case MOTHER: return "ತಾಯಿ ಹೆಸರು";
            case PROVINCE: return "ಜಿಲ್ಲೆ";
            case DEATH: return "ಕಾಲವಾದ ಸ್ಥಳ ಮತ್ತು ದಿನ";
            case VILLAGE: return "ಗ್ರಾಮ";
            case FATHER: return "ತಂದೆ ಹೆಸರು";
            case TIME: return "ಕಾಲ";
            case OTHER_WORKS: return "ವಚನಗಳಲ್ಲದೆ ಇತರ ಲಭ್ಯ ಕೃತಿಗಳು";
            case CHILDREN: return "ಮಕ್ಕಳು: ಅವರ ಹೆಸರು";
            case WORK: return "ಕಾಯಕ";
            case SPOUSE: return "ಪತಿ: ಪತ್ನಿಯ ಹೆಸರು";
            case AVAILABLE_KRUTHI: return "ಲಭ್ಯ ವಚನಗಳ ಸಂಖ್ಯೆ";
            case ANKITHA: return "ಅಂಕಿತ";
            case TALUK: return "ತಾಲ್ಲೂಕು";
            case SPECIALITY: return "ಕೃತಿಯ ವೈಶಿಷ್ಟ್ಯ";
            case NAME: return "ದಾಸರ ಹೆಸರು";
            case GURU: return "ಗುರುವಿನ ಹೆಸರು";
            case VRUNDAVAN: return "ವೃಂದಾವನ ಇರುವ ಸ್ಥಳ";
            case OLD_NAME: return "ಪೂರ್ವಾಶ್ರಮದ ಹೆಸರು";
            case ROOPA: return "ರೂಪ";
            case OTHER: return "ಇತರೆ";
        }
        return null;
    }

    public enum KEYS {ID,ANKITHA,ASHRAYA,SIBLINGS,TIME,DEATH,OTHER_WORKS,
        SPECIALITY,GURU,VILLAGE,BIRTH_PLACE,PROVINCE,FATHER,MOTHER,TALUK,
        NAME,SPOUSE,OLD_NAME,CHILDREN,ROOPA,AVAILABLE_KRUTHI,VRUNDAVAN,WORK,OTHER
    }

    private int id;
    private  EnumMap<KEYS,String> details;

    KathruDetails(int id, EnumMap<KEYS, String> details) {
        this.id  = id;
        this.details = details;
    }

    public String getName() {
        return details.get(KEYS.NAME);
    }

    public String getSiblings() {
        return details.get(KEYS.SIBLINGS);
    }

    public String getBirthPlace() {
        return details.get(KEYS.BIRTH_PLACE);
    }

    public String getMother() {
        return details.get(KEYS.MOTHER);
    }

    public String getProvince() {
        return details.get(KEYS.PROVINCE);
    }

    public String getVillage() {
        return details.get(KEYS.VILLAGE);
    }

    public String getFather() {
        return details.get(KEYS.FATHER);
    }

    public String getDeath() {
        return details.get(KEYS.DEATH);
    }

    public String getTime() {
        return details.get(KEYS.TIME);
    }

    public String getOtherWorks() {
        return details.get(KEYS.OTHER_WORKS);
    }

    public String getChildren() {
        return details.get(KEYS.CHILDREN);
    }

    public String getWork() {
        return details.get(KEYS.WORK);
    }

    public String getSpuose() {
        return details.get(KEYS.SPOUSE);
    }

    public String getAvailableKruthi() {
        return details.get(KEYS.AVAILABLE_KRUTHI);
    }

    public String getAnkitha() {
        return details.get(KEYS.ANKITHA);
    }

    public String getTaluk() {
        return details.get(KEYS.TALUK);
    }

    public String getSpeciality() {
        return details.get(KEYS.SPECIALITY);
    }

    public String getGuru()
    {
        return details.get(KEYS.GURU);
    }

    public String getVrundavan()
    {
        return details.get(KEYS.VRUNDAVAN);
    }

    public String getOldname()
    {
        return details.get(KEYS.OLD_NAME);
    }

    public String getRoopa()
    {
        return details.get(KEYS.ROOPA);
    }

    public String getOther()
    {
        return details.get(KEYS.OTHER);
    }

    public int getId() {
        return id;
    }
}
