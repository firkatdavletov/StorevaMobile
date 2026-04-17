//
//  HomeContent.swift
//  iosApp
//
//  Created by Фиркат Давлетов on 12/12/2025.
//  Copyright © 2025 orgName. All rights reserved.
//


import SwiftUI
import Shared

struct HomeContent: View {
    let userName: String?
    let addressString: String
    let deliveryPrice: Int64
    let currentOrders: [OrderUIModel]
    let categories: [CategoryModel]
    let totalAmount: Int64
    let productsPrice: Int64
    let freeDeliveryPrice: Int64?
    let storeIsClosed: Bool
    let onChangeAddressClicked: () -> Void
    let onCategoryClicked: (CategoryModel) -> Void
    let onCartButtonClicked: () -> Void
    let onPersonClicked: () -> Void
    let onOrderClicked: (Int64) -> Void
    let onAddToCart: (Shared.ProductModel) -> Void
    let onRemoveFromCart: (Shared.ProductModel) -> Void
    let onShowDetails: (Shared.ProductModel) -> Void

    private let contentHorizontalPadding: CGFloat = 16
    private let categoryGridSpacing: CGFloat = 8

    private var categoryCardWidth: CGFloat {
        let totalHorizontalPadding = contentHorizontalPadding * 2
        let totalSpacing = categoryGridSpacing * 2
        return (UIScreen.main.bounds.width - totalHorizontalPadding - totalSpacing) / 3
    }

    private var columns: [GridItem] {
        Array(
            repeating: GridItem(
                .fixed(categoryCardWidth),
                spacing: categoryGridSpacing,
                alignment: .top
            ),
            count: 3
        )
    }

    var body: some View {
        VStack(spacing: 0) {
            headerView

            ScrollView {
                VStack(spacing: 12) {
                    if storeIsClosed {
                        storeIsClosedView
                    }

                    ordersPagerItem

                    LazyVGrid(columns: columns, spacing: categoryGridSpacing) {
                        ForEach(categories, id: \.id) { category in
                            HomeCategoryCard(
                                title: category.title,
                                imageUrl: category.imageUrl,
                                cardWidth: categoryCardWidth
                            )
                            .onTapGesture {
                                onCategoryClicked(category)
                            }
                        }
                    }
                    .frame(maxWidth: .infinity, alignment: .leading)
                }
                .frame(maxWidth: .infinity, alignment: .leading)
                .padding(.horizontal, contentHorizontalPadding)
                .padding(.top, 12)
                .padding(.bottom, 8)
            }
        }
        .safeAreaInset(edge: .bottom) {
            if totalAmount > 0 {
                VStack(spacing: 8) {
                    if let freeDeliveryPrice, productsPrice < freeDeliveryPrice {
                        let remaining = freeDeliveryPrice - productsPrice
                        let progress = max(0, min(1, productsPrice / freeDeliveryPrice))

                        VStack(alignment: .leading, spacing: 4) {
                            Text("Добавьте ещё \(remaining.asCurrency()) для бесплатной доставки")
                                .font(AppTypography.bodyMedium)
                                .foregroundColor(.primaryContainer)

                            ProgressView(value: Double(progress))
                                .progressViewStyle(.linear)
                                .tint(Color.primaryContainer)
                        }
                        .padding()
                    }

                    PrimaryButton(
                        title: "\(totalAmount.asCurrency())",
                        onClick: onCartButtonClicked,
                        enabled: true
                    )
                    .padding()
                }
                .background(Color.background)
            }
        }
        .background(Color.background)
    }
}

extension HomeContent {
    private var headerView: some View {
        HStack(alignment: .top,spacing: 8) {
            VStack(alignment: .leading, spacing: 8) {

                Text(addressString)
                    .font(AppTypography.titleLarge)
                    .foregroundColor(.onPrimaryContainer)

                HStack(spacing: 16) {
                    Text("Доставка: " + deliveryPrice.asCurrency())
                        .font(AppTypography.bodyMedium)
                        .foregroundColor(.onPrimaryContainer)

                    Text("Изменить")
                        .font(AppTypography.bodySmall)
                        .padding(.vertical, 4)
                        .padding(.horizontal, 6)
                        .background(Color.primaryContainer)
                        .foregroundColor(Color.onPrimaryContainer)
                        .cornerRadius(12)
                        .padding(1)
                        .background(Color.onPrimaryContainer)
                        .cornerRadius(12)
                }
            }
            .frame(maxWidth: .infinity, alignment: .leading)
            .onTapGesture { onChangeAddressClicked() }

            Button(action: onPersonClicked) {
                Image(systemName: "person.circle")
                    .font(.title2)
                    .foregroundStyle(Color.onPrimaryContainer)
            }
        }
        .padding(.horizontal, 16)
        .padding(.vertical, 16)
        .background(Color.primaryContainer)
    }
}

extension HomeContent {
    private var ordersPagerItem: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            LazyHStack {
                ForEach(currentOrders, id: \.self) { order in
                    HomeOrderView(
                        orderNumber: order.number,
                        status: order.status,
                        amount: order.amount
                    )
                    .padding(.vertical, 2)
                    .frame(width: UIScreen.main.bounds.width - 32)
                    .onTapGesture {
                        onOrderClicked(order.id)
                    }
                }
            }
        }
    }
}

extension HomeContent {
    private var storeIsClosedView: some View {
        Text("Ресторан закрыт")
            .font(AppTypography.headlineSmall)
            .foregroundStyle(Color.onBackground)
            .frame(maxWidth: .infinity, alignment: .center)
    }
}

struct HomeOrderView: View {
    let orderNumber: String
    let status: String
    let amount: Int64

    var body: some View {
        HStack {
            VStack(alignment: .leading, spacing: 8) {
                Text("Заказ №\(orderNumber)")
                    .font(AppTypography.bodyMedium)
                    .foregroundStyle(Color.onSecondaryContainer)
                Text(status)
                    .font(AppTypography.titleMedium)
                    .foregroundStyle(Color.onSecondaryContainer)
            }
            Spacer()
            Text("\(amount.asCurrency())")
                .font(AppTypography.titleLarge)
                .foregroundStyle(Color.onSecondaryContainer)
        }
        .padding(12)
        .frame(maxWidth: .infinity)
        .background(Color.background)
        .clipShape(RoundedRectangle(cornerRadius: 12))
        .overlay(
            RoundedRectangle(cornerRadius: 12)
                .stroke(Color.primaryContainer, lineWidth: 0)
        )
    }
}

private struct HomeCategoryCard: View {
    let title: String
    let imageUrl: String?
    let cardWidth: CGFloat

    private var imageURL: URL? {
        imageUrl.flatMap(URL.init(string:))
    }

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            categoryImage
                .frame(width: cardWidth, height: 96)
                .clipShape(RoundedRectangle(cornerRadius: 12, style: .continuous))

            Text(title)
                .font(AppTypography.bodyMedium)
                .foregroundStyle(Color.onBackground)
                .lineLimit(2)
                .multilineTextAlignment(.leading)
        }
        .frame(width: cardWidth, alignment: .top)
        .contentShape(RoundedRectangle(cornerRadius: 16, style: .continuous))
    }

    @ViewBuilder
    private var categoryImage: some View {
        if let imageURL {
            AsyncImage(url: imageURL) { phase in
                switch phase {
                case .success(let image):
                    image
                        .resizable()
                        .aspectRatio(contentMode: .fill)
                        .frame(width: cardWidth, height: 96)
                        .clipped()
                case .empty:
                    placeholder
                case .failure:
                    placeholder
                @unknown default:
                    placeholder
                }
            }
        } else {
            placeholder
        }
    }

    private var placeholder: some View {
        ZStack {
            Color.gray.opacity(0.2)
            Image(systemName: "photo")
                .font(.title3)
                .foregroundStyle(Color.gray)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
}
