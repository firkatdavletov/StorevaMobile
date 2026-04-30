//
//  ProfileTabView.swift
//  iosApp
//
//  Created by Фиркат Давлетов on 30/04/2026.
//  Copyright © 2026 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct ProfileTabView: View {
    let component: ProfileTabComponent
    
    init(component: ProfileTabComponent) {
        self.component = component
    }
    
    var body: some View {
        Text("Profile tab!")
    }
}
