package com.be.member.dto.res;

import com.be.member.domain.Member;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MemberDefaultResDto {

    private long memberNum;
    private String memberID;
    private String Name;
    private String Email;
    private String Birth;
    private String Gender;
    private int Preference;
    private int InvestScore;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String RegDate;

    public MemberDefaultResDto(Member member) {
        this.memberNum = member.getMemberNum();
        this.memberID = member.getMemberID();
        this.Email = member.getEmail();
        this.Name = member.getMemberName();
        this.Birth = member.getBirth();
        this.Gender = member.getGender();
        this.Preference = member.getPreference();
        this.InvestScore = member.getInvestScore();
        this.RegDate = member.getRegDate();

    }
}
