package interfaces

import (
	"github.com/google/uuid"
	"github.com/mossT8/backend/internal/application/command"
	"github.com/mossT8/backend/internal/application/query"
)

type SellerService interface {
	CreateSeller(sellerCommand *command.CreateSellerCommand) (*command.CreateSellerCommandResult, error)
	FindAllSellers() (*query.SellerQueryListResult, error)
	FindSellerById(id uuid.UUID) (*query.SellerQueryResult, error)
	UpdateSeller(updateCommand *command.UpdateSellerCommand) (*command.UpdateSellerCommandResult, error)
	DeleteSeller(id uuid.UUID) error
}
