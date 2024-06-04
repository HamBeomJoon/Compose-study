package com.example.lazycolumn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomItem(person: Person) {
    Row(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        Text(text = "이름: ", fontSize = 28.sp)
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = person.firstName,
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = person.lastName,
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal
            )
        }
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = "나이: ", fontSize = 28.sp)
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "${person.age}",
            color = Color.Black,
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
@Preview
fun CustomItemPreview() {
    CustomItem(
        person = Person(
            id = 0,
            firstName = "John",
            lastName = "Johnson",
            age = 20
        )
    )
}