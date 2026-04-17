//
//  CatalogContent.swift
//  iosApp
//
//  Created by Фиркат Давлетов on 16/12/2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct CatalogContent: View {
    let title: String
    let products: [Shared.ProductModel]
    let amount: Int64
    let productsPrice: Int64
    let freeDeliveryPrice: Int64?
    let onAddToCart: (Shared.ProductModel) -> Void
    let onRemove: (Shared.ProductModel) -> Void
    let onCartButtonClicked: () -> Void
    let onBackButtonClicked: () -> Void
    let onProductCardClicked: (Shared.ProductModel) -> Void
    
    let columns = [
        GridItem(.flexible()),
        GridItem(.flexible())
    ]
    
    var body: some View {
        VStack {
            HStack {
                Button(action: onBackButtonClicked) {
                    Image(systemName: "chevron.left")
                        .foregroundColor(Color.onPrimaryContainer)
                }
                Spacer()
                Text(title)
                    .font(AppTypography.titleLarge)
                    .bold()
                    .foregroundColor(Color.onPrimaryContainer)
                Spacer()
            }
            .padding()
            .background(Color.primaryContainer)
            
            ScrollView {
                LazyVGrid(columns: columns, spacing: 16) {
                    ForEach(products, id: \.id) { product in
                        ProductCardView(product: product) { id  in
                            onAddToCart(product)
                        } onRemove: { id in
                            onRemove(product)
                        } onShowDetails: { id in
                            onProductCardClicked(product)
                        }
                    }
                }
                .padding(.horizontal)
            }
        }
        .safeAreaInset(edge: .bottom) {
            if amount > 0 {
                VStack(spacing: 8) {

                    if freeDeliveryPrice != nil && productsPrice < freeDeliveryPrice! {
                        let remaining = freeDeliveryPrice! - productsPrice
                        let progress = Double(productsPrice) / Double(freeDeliveryPrice!)

                        VStack(alignment: .leading, spacing: 4) {
                            Text("Добавьте ещё \(remaining.asCurrency()) для бесплатной доставки")
                                .font(AppTypography.bodyMedium)
                                .foregroundColor(.primaryContainer)
                                    ProgressView(value: progress)
                                        .progressViewStyle(.linear)
                                        .tint(Color.primaryContainer)
                                }
                                .padding()
                    }

                    PrimaryButton(
                        title: "\(amount.asCurrency())",
                        onClick: onCartButtonClicked,
                        enabled: true
                    )
                    .padding()
                }
            }
        }
    }
}
