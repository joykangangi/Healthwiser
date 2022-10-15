package com.example.healthwiser.presentation.ui.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import java.util.regex.Pattern

//Todo - broken url

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
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.medical_equipment),
            contentDescription = stringResource(id = R.string.medical_equipment),
            alignment = Alignment.Center,
            modifier = Modifier
                .border(border = BorderStroke(2.dp, Color.Blue), shape = CircleShape)
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

            HyperlinkText(fullText = "Read More Here", linkText = "Here", hyperlink = disease.more )
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
    if (details != null) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h4
        )
        Divider(modifier.padding(horizontal = 4.dp))
        Text(
            text = details,
            style = fontStyle,
            fontWeight = fontWeight
        )
    }
    else Text(text = "")
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

        val hyperlinkRegex = "\\b(https?://[-a-zA-Z\\\\d+&@#/%?=~_|!:, .;]*[-a-zA-Z\\\\d+&@#/%=~_|])"
        val pattern = Pattern.compile(hyperlinkRegex,Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(hyperlink)
        val foundUrl = fullText.substring(matcher.start(0), matcher.end(0))

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
            annotation = foundUrl,
            start = startIndex,
            end = endIndex
        )
    }

    val uriHandler = LocalUriHandler.current

    ClickableText(
        text = annotatedString,
        onClick = {
            annotatedString
                .getStringAnnotations("URL", start = it, end = it)
                .firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        },
        modifier = modifier.padding(top = 5.dp)
    )
}