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

import java.util.ArrayList;
import java.util.List;

public interface DatabaseReadAccess {
    KathruMini getKathruMiniById(int id);
    ArrayList<KathruMini> getAllKathruMinis();
    ArrayList<PadaMini> getPadaMinisByKathruId (int kathruId);
    String getKathruNameById(int kathruId);
    Pada getPada(int id);
    ArrayList<PadaMini> getFavoritePadaMinis();
    ArrayList<KathruMini> getFavoriteKathruMinis();
    KathruDetails getKathruDetails (int kathruId);
    List<PadaMini> searchForPada(String queryString, String kathruName, boolean isPartial);
}
