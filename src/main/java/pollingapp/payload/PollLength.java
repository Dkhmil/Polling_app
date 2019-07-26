package pollingapp.payload;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

public class PollLength {

    @NotNull
    @Range(min=0, max=7)
    private Integer days;

    @NotNull
    @Range(min=0, max=23)
    private Integer hours;

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }
}
