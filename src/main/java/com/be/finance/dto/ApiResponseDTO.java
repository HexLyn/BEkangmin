package com.be.finance.dto;

import java.util.List;

public class ApiResponseDTO {
    private ResultDTO result;

    // Getter 및 Setter
    public ResultDTO getResult() {
        return result;
    }

    public void setResult(ResultDTO result) {
        this.result = result;
    }

    // 내부 클래스인 ResultDTO 추가
    public static class ResultDTO {
        private int totalCount;
        private List<BaseProductDTO> baseList;
        private List<OptionProductDTO> optionList;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public List<BaseProductDTO> getBaseList() {
            return baseList;
        }

        public void setBaseList(List<BaseProductDTO> baseList) {
            this.baseList = baseList;
        }

        public List<OptionProductDTO> getOptionList() {
            return optionList;
        }

        public void setOptionList(List<OptionProductDTO> optionList) {
            this.optionList = optionList;
        }
    }
}
