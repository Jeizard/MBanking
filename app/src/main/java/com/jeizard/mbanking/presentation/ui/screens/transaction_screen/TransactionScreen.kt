package com.jeizard.mbanking.presentation.ui.screens.transaction_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jeizard.mbanking.domain.entities.Transaction
import com.jeizard.mbanking.domain.entities.TransactionStatus
import com.jeizard.mbanking.presentation.ui.screens.common.DateTextField
import com.jeizard.mbanking.presentation.ui.screens.common.view_models.TransactionViewModel
import com.jeizard.mbanking.presentation.ui.theme.MBankingTheme
import com.jeizard.mbanking.utils.DollarVisualTransformation
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionScreen(navController: NavHostController, viewModel: TransactionViewModel = koinViewModel()) {
    val transaction by viewModel.selectedTransaction.collectAsState()
    val newTransaction = transaction ?: Transaction(company = "", number = "", date = "", amount = 0.0, status = TransactionStatus.Executed)

    var company by remember { mutableStateOf(newTransaction.company) }
    var number by remember { mutableStateOf(newTransaction.number) }
    var date by remember { mutableStateOf(newTransaction.date) }
    var amount by remember { mutableStateOf(newTransaction.amount.toString()) }
    var status by remember { mutableStateOf(newTransaction.status) }

    var isAddTransactionEnabled by remember { mutableStateOf(false) }
    val isExistingTransaction = transaction != null

    val onValueChange: () -> Unit = {
        isAddTransactionEnabled = company.isNotEmpty() && number.isNotEmpty() && date.isNotEmpty()
    }

    MBankingTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = if (isExistingTransaction) "Transaction" else "Add Transaction",
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
                )
            },
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp)
                ) {
                    LabeledTextField(
                        label = "Transaction was applied in",
                        value = company,
                        onValueChange = {
                            company = it
                            onValueChange()
                        },
                        enabled = !isExistingTransaction
                    )
                    LabeledTextField(
                        label = "Transaction number",
                        value = number,
                        onValueChange = {
                            number = it
                            onValueChange()
                        },
                        enabled = !isExistingTransaction
                    )
                    DateTextField(
                        label = "Date",
                        placeholder = "",
                        value = date,
                        onValueChange = {
                            date = it
                            onValueChange()
                        },
                        onDateSelected = { selectedDate ->
                            date = selectedDate
                            onValueChange()
                        },
                        error = false,
                        onCalendarVisibilityChanged = { },
                        enabled = !isExistingTransaction
                    )
                    StatusDropdownMenu(
                        label = "Transaction status",
                        status = status, onStatusSelected = { status = it },
                        enabled = !isExistingTransaction
                    )
                    LabeledTextField(
                        label = "Amount",
                        value = amount,
                        onValueChange = {
                            amount = it
                            onValueChange()
                        },
                        keyboardType = KeyboardType.Decimal,
                        enabled = !isExistingTransaction,
                        visualTransformation = DollarVisualTransformation()
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            if (!isExistingTransaction) {
                                if(amount.toString().startsWith('.')){
                                    amount = "0$amount"
                                }
                                viewModel.addTransaction(
                                    Transaction(
                                        company = company,
                                        number = number,
                                        date = date,
                                        amount = amount.toDouble(),
                                        status = status
                                    )
                                )
                            }
                            navController.navigateUp()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = Color.White
                        ),
                        enabled = isAddTransactionEnabled || isExistingTransaction
                    ) {
                        Text(
                            text = if (isExistingTransaction) "Okay" else "Add Transaction",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun TransactionScreenPreview() {
    TransactionScreen(
        navController = rememberNavController()
    )
}