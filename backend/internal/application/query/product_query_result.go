package query

import "github.com/mossT8/backend/internal/application/common"

type ProductQueryResult struct {
	Result *common.ProductResult
}

type ProductQueryListResult struct {
	Result []*common.ProductResult
}
