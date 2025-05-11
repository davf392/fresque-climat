package com.davidfz.animfresque.ui.community.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PinDrop
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.PinDrop
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.davidfz.animfresque.ui.community.components.LocalGroupsSection
import com.davidfz.animfresque.ui.community.components.MembersSection
import com.davidfz.animfresque.ui.theme.FresqueClimatColors

data class TabItem(
    val title: String = "",
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@Composable
fun CommunityScreen(
    modifier: Modifier = Modifier,
) {
    val pages = listOf(
        TabItem("Annuaire", Icons.Filled.People, Icons.Outlined.People),
        TabItem("Local", Icons.Filled.PinDrop, Icons.Outlined.PinDrop),
    )
    val pagerState = rememberPagerState(pageCount = { pages.size })
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
        }
    }
    Column(modifier = modifier.fillMaxSize()) {
        TabRow(selectedTabIndex) {
            pages.forEachIndexed { index, tab ->
                Tab(
                    text = {
                        Text(
                            text = tab.title,
                            fontSize = 18.sp,
                            color = FresqueClimatColors.Primary
                        )
                    },
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) { page ->
            when (page) {
                0 -> MembersSection()
                1 -> LocalGroupsSection()
            }
        }
    }
}

@Preview
@Composable
fun CommunityScreenPreview() {
    CommunityScreen(
        modifier = Modifier.background(Color.White)
    )
}