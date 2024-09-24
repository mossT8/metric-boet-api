package query

import "github.com/mossT8/backend/internal/application/common"

type SellerQueryResult struct {
	Result *common.SellerResult
}

type SellerQueryListResult struct {
	Result []*common.SellerResult
}
