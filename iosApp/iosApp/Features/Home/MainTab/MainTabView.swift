//
//  MainTabView.swift
//  iosApp
//
//  Created by Фиркат Давлетов on 30/04/2026.
//  Copyright © 2026 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct MainTabView: View {
    let component: MainTabComponent
    
    init(component: MainTabComponent) {
        self.component = component
    }
    
    var body: some View {
        Text("Main tab!")
    }
}
