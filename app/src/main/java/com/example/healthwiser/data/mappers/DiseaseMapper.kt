package com.example.healthwiser.data.mappers

import com.example.healthwiser.data.remote.dto.Disease
import com.example.healthwiser.domain.model.DiseasesModel

/**
 * extension functions to transform entity to normal kt. file
 * Domain is the inner-most part
 */

fun Disease.toDiseasesModel(): DiseasesModel {
    return DiseasesModel(
        name = name,
        dataUpdatedAt = dataUpdatedAt,
        diagnosis = diagnosis,
        facts = facts,
        more = more,
        treatment = treatment,
        prevention = prevention,
        symptoms = symptoms,
        transmission = transmission,
        isActive = isActive
    )
}

fun DiseasesModel.toDisease(): Disease {
     return Disease(
         name = name,
         dataUpdatedAt = dataUpdatedAt,
         diagnosis = diagnosis,
         facts = facts,
         more = more,
         transmission = transmission,
         treatment = treatment,
         symptoms = symptoms,
         prevention = prevention,
         isActive = isActive
     )
}