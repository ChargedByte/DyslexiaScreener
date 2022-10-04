package fi.metropolia.capslock.dyslexiascreener;

public class TextRecognitionImages {
    private int[] imageIDs = {R.drawable.smudgedahven, R.drawable.smudgedapina, R.drawable.smudgedorava, R.drawable.smudgedmato};
    private String[] imageTexts = {"ahven", "apina", "orava", "mato"};

    public int[] getImageIDs() {
        return imageIDs;
    }

    public String[] getImageTexts() {
        return imageTexts;
    }

    public void removeImageID(int removeIndex) {
        int[] newImageIDs = {0,0,0,0};

        for (int i = 0;i<imageIDs.length;i++){
            if (i != removeIndex){
                newImageIDs[i] = imageIDs[i];
            }
        }
        this.imageIDs = newImageIDs;
    }

    public void removeImageText(int removeIndex){
        String[] newImageTexts = {"","","",""};
        for (int i = 0;i<imageIDs.length;i++){
            if (i != removeIndex){
                newImageTexts[i] = imageTexts[i];
            }
        }
        this.imageTexts = newImageTexts;
    }
}
