package jjh.sample.navigator.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import jjh.sample.navigator.main.mvi.MainIntent

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier = Modifier,
            text = "${state.count}",
            fontSize = 100.sp,
        )

        Spacer(modifier = Modifier.size(200.dp))

        Row() {
            Text(
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(all = 20.dp)
                    .clickable {
                        viewModel.submitIntent(MainIntent.Increment)
                    },
                text = "+",
                color = Color.Black,
            )
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(all = 20.dp)
                    .clickable {
                        viewModel.submitIntent(MainIntent.Decrement)
                    },
                text = "-",
                color = Color.Black,
            )
        }
    }
}
