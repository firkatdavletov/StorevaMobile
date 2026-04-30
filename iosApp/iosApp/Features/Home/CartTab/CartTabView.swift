//
//  CartTabView.swift
//  iosApp
//
//  Created by Фиркат Давлетов on 30/04/2026.
//  Copyright © 2026 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct CartTabView: View {
    let component: CartTabComponent
    
    init(component: CartTabComponent) {
        self.component = component
    }
    
    var body: some View {
        Text("Cart tab!")
    }
}
