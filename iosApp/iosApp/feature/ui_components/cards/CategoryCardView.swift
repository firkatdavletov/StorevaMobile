//
//  CategoryCardView.swift
//  iosApp
//
//  Created by Фиркат Давлетов on 09.05.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct CategoryCardView: View {
    let title: String
    let products: [Shared.ProductModel]
    // Две колонки для LazyVGrid
    let columns = [
        GridItem(.flexible()),
        GridItem(.flexible())
    ]

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(title)
                .font(.headline)
                .padding(.horizontal)
            
            LazyVGrid(columns: columns, spacing: 12) {
                ForEach(products, id: \.id) { product in
                    ProductCardView(
                        product: product,
                        onAddToCart: {id in },
                        onRemove: {id in },
                        onShowDetails: {id in}
                    )
                }
            }
            .padding(.horizontal)
        }
    }
}
