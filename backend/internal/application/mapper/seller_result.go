package mapper

import (
	"github.com/mossT8/backend/internal/application/common"
	"github.com/mossT8/backend/internal/domain/entities"
)

func NewSellerResultFromValidatedEntity(seller *entities.ValidatedSeller) *common.SellerResult {
	return NewSellerResultFromEntity(&seller.Seller)
}

func NewSellerResultFromEntity(seller *entities.Seller) *common.SellerResult {
	if seller == nil {
		return nil
	}

	return &common.SellerResult{
		Id:        seller.Id,
		Name:      seller.Name,
		CreatedAt: seller.CreatedAt,
		UpdatedAt: seller.UpdatedAt,
	}
}
