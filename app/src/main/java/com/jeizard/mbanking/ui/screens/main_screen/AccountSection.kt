package com.jeizard.mbanking.ui.screens.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeizard.mbanking.R
import com.jeizard.mbanking.ui.theme.DarkGrey
import com.jeizard.mbanking.ui.theme.MBankingTheme

@Composable
fun AccountSection() {
    val accountName: String = "Saving Account"
    val accountNumber: String = "91212129291221"
    val accountCard: String =  "•••• 1234"

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Account",
            style = MaterialTheme.typography.titleMedium
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { /* Navigate to Select The Account screen*/ },
            colors = CardDefaults.cardColors(containerColor = DarkGrey)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image_card),
                    contentDescription = "Card Image",
                    modifier = Modifier.align(Alignment.Top))
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = accountName,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                    Text(
                        text = accountNumber,
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                    Text(
                        text = accountCard,
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowRight,
                    contentDescription = "ArrowRightIcon",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AccountSectionPreview() {
    MBankingTheme {
        AccountSection()
    }
}