//
//  ProductCardView.swift
//  iosApp
//
//  Created by Фиркат Давлетов on 10.05.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct ProductCardView: View {
    let product: Shared.ProductModel
    let onAddToCart: (Shared.ProductModel) -> Void
    let onRemove: (Shared.ProductModel) -> Void
    let onShowDetails: (Shared.ProductModel) -> Void
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            RemoteImage(urlString: product.imageUrl)
                .frame(height: 112)
                .clipShape(
                    RoundedRectangle(cornerRadius: 16, style: .continuous)
                )

            Text("\(Int(product.price)) руб")
                .font(.system(size: 16, weight: .bold, design: .rounded))
                .foregroundColor(Color.onBackground)
                .padding(.horizontal, 8)

            Text(product.title)
                .font(AppTypography.bodyMedium)
                .foregroundColor(Color.onBackground)
                .padding(.horizontal, 8)

            CartButton(
                quantity: Int(product.count),
                onAdd: { onAddToCart(product) },
                onRemove: { onRemove(product) },
                foregroundColor: Color.onPrimaryContainer
            )
                .padding(.horizontal, 8)
                .padding(.bottom, 8)
                .allowsHitTesting(true)
        }
        .background(Color.surface)
        .overlay {
            RoundedRectangle(cornerRadius: 16)
                .stroke(Color.primaryContainer, lineWidth: 1)
        }
        .clipShape(RoundedRectangle(cornerRadius: 16))
        .frame(height: 234)
        .contentShape(RoundedRectangle(cornerRadius: 16))
        .onTapGesture {
            onShowDetails(product)
        }
    }
}

#Preview {
    ProductCardView(
        product: Shared.ProductModel(
            id: 0,
            title: "Pizza",
            description: nil,
            price: 530.0,
            imageUrl: nil,
            categoryId: 4,
            count: 56
        )) { Int64 in
            
        } onRemove: { Int64 in
            
        } onShowDetails: { id in
            
        }
        .padding()

}
