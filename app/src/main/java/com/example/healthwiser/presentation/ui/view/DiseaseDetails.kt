package com.example.healthwiser.presentation.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.healthwiser.R
import com.example.healthwiser.domain.repository.HealthViewModel

@Composable
fun DetailsScreen(
    diseaseIndex: String?,
    healthViewModel: HealthViewModel
) {
    val disease = diseaseIndex?.let { healthViewModel.getDisease(it.toInt()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(id = R.drawable.medical_equipment),
            contentDescription = stringResource(id = R.string.medical_equipment),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .size(250.dp)
        )

        if (disease != null) {
            CustomText(title = disease.name, details = disease.facts.joinToString(" "))
            CustomText(title = "Symptoms", details = disease.symptoms)
            CustomText(title = "Transmission", details = disease.transmission)
            CustomText(title = "Diagnosis", details = disease.diagnosis)
            CustomText(title = "Treatment", details = disease.treatment)
            CustomText(title = "Prevention", details = disease.prevention)

            HyperlinkText(fullText = "Read More Here", linkText = "Here", hyperlink = disease.more)
        }
    }
}

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    title: String,
    details: String?,
    fontWeight: FontWeight = FontWeight.Normal,
    fontStyle: TextStyle = MaterialTheme.typography.subtitle1
) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.h4
    )
    Divider(modifier.padding(horizontal = 4.dp))
    Text(
        text = details ?: "",
        style = fontStyle,
        fontWeight = fontWeight
    )
}

@Composable
fun HyperlinkText(
    modifier: Modifier = Modifier,
    fullText: String,
    linkText: String,
    linkTextColor: Color = Color.Blue,
    linkTextFontWeight: FontWeight = FontWeight.Medium,
    linkTextDecoration: TextDecoration = TextDecoration.Underline,
    hyperlink: String,
    fontSize: TextUnit = TextUnit.Unspecified
) {
    val annotatedString = buildAnnotatedString {
        append(fullText)
        val startIndex = fullText.indexOf(linkText)
        val endIndex = startIndex + linkText.length
        addStyle(
            style = SpanStyle(
                color = linkTextColor,
                fontSize = fontSize,
                fontWeight = linkTextFontWeight,
                textDecoration = linkTextDecoration,
            ),
            start = startIndex,
            end = endIndex
        )

        addStringAnnotation(
            tag = "URL",
            annotation = hyperlink,
            start = startIndex,
            end = endIndex
        )
    }

    val uriHandler = LocalUriHandler.current

    ClickableText(
        text = annotatedString,
        onClick = {
            annotatedString
                .getStringAnnotations("URL", it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        },
        modifier = modifier
    )
}



