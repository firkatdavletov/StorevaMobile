//
//  HomeView.swift
//  iosApp
//
//  Created by Фиркат Давлетов on 08.05.2025.
//  Copyright © 2025 orgName. All rights reserved.
//P

import SwiftUI
import Shared

struct HomeContent: View {
    let component: HomeComponent
    
    @StateValue private var state: HomeComponentState
    
    init(component: HomeComponent) {
        self.component = component
        _state = StateValue(component.state)
    }
    
    var body: some View {
        TabView(
            selection: Binding(
                get: { Int(state.selectedTabIndex) },
                set: { tab in
                    component.onTabSelectedByIndex(index: Int32(tab))
                }
            )
        ) {
            MainTabView(component: component.mainComponent)
                .tabItem {
                    Label("Главная", systemImage: "house")
                }
                .tag(0)

            CatalogTabView(component: component.catalogComponent)
                .tabItem {
                    Label("Каталог", systemImage: "cart")
                }
                .badge(Int(state.cartBadgeCount))
                .tag(1)

            CartTabView(component: component.cartComponent)
                .tabItem {
                    Label("Корзина", systemImage: "list.bullet.rectangle")
                }
                .tag(2)

            ProfileTabView(component: component.profileComponent)
                .tabItem {
                    Label("Профиль", systemImage: "person")
                }
                .tag(3)
            }
        }
}
