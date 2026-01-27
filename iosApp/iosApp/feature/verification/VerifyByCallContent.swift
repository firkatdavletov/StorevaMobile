//
//  VerificationContent.swift
//  iosApp
//
//  Created by Фиркат Давлетов on 10/01/2026.
//  Copyright © 2026 orgName. All rights reserved.
//

import SwiftUI

struct VerifyByCallContent: View {
    let callPhone: String
    let isLoading: Bool
    let onCallPhoneClicked: () -> Void
    let onBack: () -> Void
    let onAppBecameActive: () -> Void
    
    @Environment(\.scenePhase) private var scenePhase
    
    var body: some View {
        ZStack {
            Color.primaryContainer
                .ignoresSafeArea()
            
            ScrollView {
                VStack(spacing: 0) {
                            
                    Spacer(minLength: 40)
                    
                    HStack {
                        IconButton(
                            systemName: "arrow.backward",
                            tint: Color.onPrimaryContainer,
                            foreground: Color.primaryContainer,
                            action: onBack
                        )
                        Spacer()
                    }
                    .padding(.horizontal)
                    
                    ZStack {
                        Circle()
                            .fill(Color.white)
                            .frame(width: 64, height: 64)

                        Image.logo
                            .resizable()
                            .scaledToFit()
                            .padding(.horizontal, 16)
                            .frame(width: 72, height: 72)
                            .clipShape(Circle())
                    }
                            
                    if (isLoading) {
                        loadingView()
                    } else {
                        titleView()
                            .padding(.horizontal)
                    }
                            
                    Spacer(minLength: 100)
                }
                .frame(maxWidth: .infinity)
            }
        }
//        .safeAreaInset(edge: .bottom) {
//            bottomCallButton()
//        }
        .onChange(of: scenePhase) { newPhase in
            switch newPhase {
            case .active:
                // Приложение вернулось в foreground
                onAppBecameActive()
            case .background:
                // Ушло в background
                break
            case .inactive:
                break
            @unknown default:
                break
            }
        }
    }
    
    @ViewBuilder
    private func titleView() -> some View {
        VStack {
            Text("Подтвердите номер телефона")
                .foregroundStyle(Color.onPrimaryContainer)
                .font(AppTypography.headlineMedium)
                .padding(8)
                .multilineTextAlignment(.center)
            
            Text("Нажмите Позвонить. Мы автоматически сбросим вызов — отвечать не нужно. Подтверждение произойдёт автоматически.")
                .foregroundStyle(Color.onPrimaryContainer)
                .font(AppTypography.bodyMedium)
                .padding(.bottom, 24)
                .multilineTextAlignment(.center)
            
            SecondaryButton(
                title: "Позвонить",
                onClick: {
                    onCallPhoneClicked()
                    doCallPhone()
                },
                enabled: !isLoading
            )
            .disabled(isLoading)
            Text("Звонок бесплатный. Деньги не списываются.")
                .foregroundStyle(Color.onPrimaryContainer)
                .font(AppTypography.bodyMedium)
        }
    }
    
    @ViewBuilder
    private func loadingView() -> some View {
        VStack {
            Text("Подтверждаем номер...")
                .foregroundStyle(Color.onPrimaryContainer)
                .font(AppTypography.headlineSmall)
                .padding(.bottom, 8)
                .multilineTextAlignment(.center)
            Text("Это займет несколько секунд")
                .foregroundStyle(Color.onPrimaryContainer)
                .font(AppTypography.bodyMedium)
                .padding(.bottom, 24)
            ProgressView()
                .font(.system(size: 24, weight: .bold))
                .foregroundColor(.onPrimaryContainer)
        }
    }
    
    @ViewBuilder
    private func bottomCallButton() -> some View {
        VStack {
            PrimaryButton(
                title: "Вернуться назад",
                onClick: {
                    onBack()
                },
                enabled: true
            )
        }
        .padding(.horizontal)
    }
    
    private func doCallPhone() {
        let formattedNumber = "+" + callPhone.replacingOccurrences(of: " ", with: "")
        if let url = URL(string: "tel://\(formattedNumber)"),
            UIApplication.shared.canOpenURL(url) {
            UIApplication.shared.open(url)
        }
    }
}
