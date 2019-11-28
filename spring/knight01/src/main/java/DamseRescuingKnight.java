public class DamseRescuingKnight implements Knight {
    private RescueDamselQuest quest;
    public DamseRescuingKnight(){
        this.quest = new RescueDamselQuest();
    }
    public void embarkOnQuest() {
        quest.embark();
    }
}
