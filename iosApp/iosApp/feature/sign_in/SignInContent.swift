//
//  SignInContent.swift
//  iosApp
//
//  Created by Фиркат Давлетов on 16/12/2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct SignInContent: View {
    let onPhoneNumberEntered: (String) -> Void
    let isLoading: Bool
    let authTypes: [String]
    let onAuthTypeClicked: (String) -> Void
    let onLoginButtonClicked: (String) -> Void
    let onBackClicked: () -> Void
    
    @State private var phoneNumber: String = ""
    @StateObject private var keyboard = KeyboardResponder()
    @FocusState private var isTextFieldFocused: Bool
    @Environment(\.openURL) private var openURL
    
    var body: some View {
        ZStack {
            Color.primaryContainer
                .ignoresSafeArea()
                    
            ScrollView {
                VStack(spacing: 0) {
                            
                    Spacer(minLength: 40)
                            
                    titleView()
                        .padding(.horizontal)
                            
                    phoneInputCardView()
                        .preferredColorScheme(.light)
                        .padding(.horizontal)
                        .padding(.top, 24)
                            
                    Spacer(minLength: 100)
                }
                .frame(maxWidth: .infinity)
            }
            .scrollDismissesKeyboard(.interactively)
        }
        .safeAreaInset(edge: .bottom) {
            bottomLoginButton()
        }
        .onAppear {
            DispatchQueue.main.asyncAfter(deadline: .now() + 0.4) {
                isTextFieldFocused = true
            }
        }
    }
    
    @ViewBuilder
    private func titleView() -> some View {
        VStack {
            HStack {
                IconButton(
                    systemName: "arrow.backward",
                    tint: Color.onPrimaryContainer,
                    foreground: Color.primaryContainer,
                    action: onBackClicked
                )
                Spacer()
            }
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
            Text("Добро пожаловать!")
                .foregroundStyle(Color.onPrimaryContainer)
                .font(AppTypography.headlineLarge)
                .padding(.bottom, 8)
            Text("Войдите, чтобы заказать вкусную еду")
                .foregroundStyle(Color.onPrimaryContainer)
                .font(AppTypography.bodyMedium)
                .padding(.bottom, 24)
        }
    }
    
    @ViewBuilder
    private func phoneInputCardView() -> some View {
        VStack {
            HStack {
                Text("Номер телефона")
                    .foregroundStyle(Color.onPrimaryContainer)
                    .font(AppTypography.titleSmall)
                Spacer()
            }
            phoneInputView()
//            authTypesButtonsView()
        }
    }
    
    @ViewBuilder
    private func authTypesButtonsView() -> some View {
        VStack {
            ForEach(authTypes, id: \.self) { type in
                PrimaryButton(
                    title: "Продолжить",
                    onClick: {
                        onAuthTypeClicked(type)
                    },
                    enabled: !isLoading
                )
            }
        }
    }
    
    @ViewBuilder
    private func phoneInputView() -> some View {
        HStack(spacing: 4) {
            Text("+7")
                .font(AppTypography.bodyLarge)
                .foregroundColor(Color.onBackground)
                .frame(alignment: .trailing)
                .padding(.leading, 16)
            TextField(
                "(999)9999999",
                text: Binding(
                    get: {
                        phoneNumber
                    },
                    set: { value in
                        phoneNumber = value
                        if (value.count < 14) {
                            onPhoneNumberEntered(value)
                        }
                    }
                )
            )
                .keyboardType(.phonePad)
                .frame(height: 62)
                .font(AppTypography.bodyLarge)
                .foregroundColor(Color.onBackground)
                .lineLimit(1)
                .focused($isTextFieldFocused)
                .onChange(of: phoneNumber) { newValue in
                    // Оставляем только цифры
                    let digits = newValue.filter { $0.isNumber }
                                
                    // Ограничиваем до 10 цифр
                    let limited = String(digits.prefix(10))
                            
                    // Форматируем: добавим ( и )
                    var result = ""
                    if !limited.isEmpty {
                        result += "("
                    }
                    if limited.count >= 1 {
                        result += String(limited.prefix(3))
                    } else {
                        result += limited
                    }
                    if limited.count >= 4 {
                        result += ") " + limited.dropFirst(3)
                    }
                                
                    phoneNumber = result
                }
        }
        .background(Color.onPrimaryContainer)
        .overlay(
            RoundedRectangle(cornerRadius: 16)
                .stroke(Color.onPrimaryContainer, lineWidth: 2)
        )
        .cornerRadius(16)
    }
    
    private func bottomLoginButton() -> some View {
        VStack {
            SecondaryButton(
                title: "Продолжить",
                onClick: {
                    onAuthTypeClicked("call")
                },
                enabled: phoneNumber.count >= 13 && !isLoading
            )
            Text("Продолжая, вы соглашаетесь с Политикой конфиденциальности и Условиями использования мобильного приложения")
                .foregroundStyle(Color.onPrimaryContainer)
                .font(AppTypography.bodySmall)
                .multilineTextAlignment(.leading)
                .frame(maxWidth: .infinity, alignment: .leading) // 1. Растягиваем на всю ширину
                .contentShape(Rectangle()) // чтобы кликабельной была вся область
                .onTapGesture {
                    if let url = URL(string: "https://yandex.ru") {
                        openURL(url) // 2. Открываем браузер
                    }
                }
        }
        .padding(.horizontal)
        .padding(.top, 12)
        .padding(.bottom, 12)
        .background(
            Color.primaryContainer
                .ignoresSafeArea(edges: .bottom)
        )
    }
}

#Preview {
    SignInContent(
        onPhoneNumberEntered: { String in
            
        },
        isLoading: false,
        authTypes: ["sms"]) { String in
            
        } onLoginButtonClicked: { String in
            
        } onBackClicked: {
            
        }
}
