//
//  LaunchView.swift
//  iosApp
//
//  Created by Фиркат Давлетов on 07.05.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct LaunchView: View {
    let component: LaunchComponent
    
    @StateValue private var state: LaunchState
    
    init(component: LaunchComponent) {
        self.component = component
        _state = StateValue(component.state)
    }
    var body: some View {
        LaunchContent(
            isLoading: state.isLoading,
            isError: state.isError,
            onRetryClicked: {
                component.onEvent(event_: LaunchEventRetryClicked())
            }
        )
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
}
